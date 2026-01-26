/*
 * Copyright (C) 2015 Max Planck Institute for Psycholinguistics, Nijmegen
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package nl.mpi.tg.eg.frinex;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.util.StringUtils;

/**
 * @since Aug 3, 2015 4:01:19 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Configuration
public class WebSecurityConfig {

    @Value("${nl.mpi.tg.eg.frinex.ldap.adUrl}")
    private String adUrl;
    @Value("${nl.mpi.tg.eg.frinex.ldap.adDomain}")
    private String adDomain;
    // @Value("${ldap.adBase}")
    // private String adBase;
    // @Value("${ldap.managerDn}")
    // private String managerDn;
    // @Value("${ldap.managerPassword}")
    // private String managerPassword;
    // @NotNull
    // fails if not found
    @Value("${nl.mpi.tg.eg.frinex.admin.securityGroup:}")
    protected String securityGroup;
    // @NotNull
    @Value("${nl.mpi.tg.eg.frinex.admin.user}")
    protected String USER;
    // @NotNull
    @Value("${nl.mpi.tg.eg.frinex.admin.password}")
    protected String PASSWORD;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
        final String[] publicPaths = {
            "/login",
            "/actuator/health",
            "/actuator/metrics",
            "/assignValue",
            "/completeValue",
            "/validate",
            "/mock_validate",
            "/mediaBlob",
            "/screenChange",
            "/timeStamp",
            "/metadata",
            "/tagEvent",
            "/tagPairEvent",
            "/stimulusResponse",
            "/groupEvent",
            "/adminpages.css",
            "/public_usage_stats",
            "/public_quick_stats",
            "/public_count_stats",
            "/public_count_csv"
            // "/replayMedia/*/*",
        };

        RequestMatcher[] publicMatchers = Arrays.stream(publicPaths)
                .map(AntPathRequestMatcher::new)
                .toArray(RequestMatcher[]::new);
        http
                .authenticationManager(authManager)
                // .authenticationManager(authenticationManager(contextSource))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(publicMatchers).permitAll()
                        // .anyRequest().authenticated()
                        // .hasRole("ROLE_AD_GROUP")
                        .anyRequest().hasAuthority(StringUtils.hasText(securityGroup) ? securityGroup : "ROLE_ADMIN"))
                .formLogin(form -> form.loginPage("/login").permitAll())
                .logout(logout -> logout.permitAll())
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler(accessDeniedHandler())
                        .authenticationEntryPoint(authenticationEntryPoint()))
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            throw new UsernameNotFoundException("No fallback permitted");
        };
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.sendRedirect(request.getContextPath() + "/login?accessDenied");
        };
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            response.sendRedirect(request.getContextPath() + "/login?error");
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        System.out.println("securityGroup: " + securityGroup);
        System.out.println("adUrl: " + adUrl);
        System.out.println("adDomain: " + adDomain);
        if (securityGroup == null || securityGroup.isBlank()) {
            System.out.println("DaoAuthenticationProvider");
            UserDetails userDetails = User.withUsername(USER)
                    .password("{noop}" + PASSWORD)
                    .roles("ADMIN")
                    .build();
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setUserDetailsService(new InMemoryUserDetailsManager(userDetails));
            return new ProviderManager(List.of(provider));
        } else {
            System.out.println("ActiveDirectoryLdapAuthenticationProvider");
            ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider(adDomain, adUrl);
            provider.setConvertSubErrorCodesToExceptions(true);
            provider.setUseAuthenticationRequestCredentials(true);
            AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
            authBuilder.authenticationProvider(provider);
            return authBuilder.build();
        }
    }
}
