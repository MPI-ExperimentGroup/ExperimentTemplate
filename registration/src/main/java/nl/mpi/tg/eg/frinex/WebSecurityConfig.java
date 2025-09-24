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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import javax.naming.directory.Attributes;
import javax.naming.Name;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;


/**
 * @since Aug 3, 2015 4:01:19 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Configuration
public class WebSecurityConfig {

    @Value("${ldap.userSearchFilter}")
    private String userSearchFilter;
    @Value("${ldap.groupSearchBase}")
    private String groupSearchBase;
    @Value("${ldap.url}")
    private String ldapUrl;
    @Value("${ldap.managerDn}")
    private String managerDn;
    @Value("${ldap.managerPassword}")
    private String managerPassword;
    @Value("${ldap.passwordAttribute}")
    private String passwordAttribute;
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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            final String[] publicPaths = {
                "/actuator/health", 
                "/assignValue", 
                "completeValue", 
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
                "/public_quick_stats"
                // "/replayMedia/*/*",
            };

        RequestMatcher[] publicMatchers = Arrays.stream(publicPaths)
            .map(AntPathRequestMatcher::new)
            .toArray(RequestMatcher[]::new);
        http
            .authorizeHttpRequests(auth -> auth
            .requestMatchers(publicMatchers).permitAll()
            .anyRequest().authenticated()
            )
            .formLogin(form -> form.loginPage("/login").permitAll())
            .logout(logout -> logout.permitAll())
            .csrf(csrf -> csrf.disable());
        return http.build();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(LdapTemplate ldapTemplate) {
        if (securityGroup == null || securityGroup.isBlank()) {
            var userDetails = User.withUsername(fallbackUser)
                                  .password("{noop}" + fallbackPassword)
                                  .roles("ADMIN")
                                  .build();
            return new ProviderManager(new InMemoryUserDetailsManager(userDetails));
        } else {
            var adProvider = new ActiveDirectoryLdapAuthenticationProvider(adDomain, ldapUrl);
            adProvider.setConvertSubErrorCodesToExceptions(true);
            adProvider.setUseAuthenticationRequestCredentials(true);

            var groupEnforcingProvider = new GroupMembershipCheckingProvider(adProvider, ldapTemplate, securityGroup);
            return new ProviderManager(groupEnforcingProvider);
        }
    }
    
    @Bean
    public LdapTemplate ldapTemplate() {
        var contextSource = new LdapContextSource();
        contextSource.setUrl(ldapUrl);
        contextSource.setBase(groupSearchBase);
        contextSource.setUserDn(managerDn);
        contextSource.setPassword(managerPassword);
        contextSource.afterPropertiesSet();
        return new LdapTemplate(contextSource);
    }
}
