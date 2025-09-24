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

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

/**
 * @since Aug 3, 2015 4:01:19 PM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Configuration
public class WebSecurityConfig {

    @NotNull
    @Value("${nl.mpi.tg.eg.frinex.admin.securityGroup}")
    protected String securityGroup;
    @NotNull
    @Value("${nl.mpi.tg.eg.frinex.admin.user}")
    protected String USER;
    @NotNull
    @Value("${nl.mpi.tg.eg.frinex.admin.password}")
    protected String PASSWORD;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/actuator/health", "/assignValue", "completeValue", "/validate", "/mock_validate", "/replayMedia/*/*", "/mediaBlob", "/screenChange", "/timeStamp", "/metadata", "/tagEvent", "/tagPairEvent", "/stimulusResponse", "/groupEvent", "/adminpages.css", "/public_usage_stats", "/public_quick_stats").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form.loginPage("/login").permitAll()
            )
            .logout(logout -> logout.permitAll())
            .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        List<UserDetails> users = new ArrayList<>();

        users.add(User.withUsername(USER)
                .password("{noop}" + PASSWORD)
                .roles("ADMIN")
                .build());

        for (String[] userEntry : new AdminUserList().getAdminUserList()) {
            users.add(User.withUsername(userEntry[0])
                    .password("{noop}" + userEntry[1])
                    .roles("ADMIN")
                    .build());
        }

        return new InMemoryUserDetailsManager(users);
    }
}
