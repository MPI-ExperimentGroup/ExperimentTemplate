/*
 * Copyright (C) 2015 Max Planck Institute for Psycholinguistics
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
package nl.mpi.tg.eg.experimentdesigner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @since Nov 16, 2015 11:57:18 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//               .authorizeRequests()
//               // todo: consider adding localhost limit to the configuration/**
//               .antMatchers("/configuration/**", "/listing/**").permitAll()
//               .anyRequest().authenticated()
//               .and()
//               .formLogin()
//               .loginPage("/login")
//               .permitAll()
//               .and()
//               .logout()
//               .permitAll();
        http.headers().frameOptions().sameOrigin()
                .and().authorizeRequests()
                .antMatchers("/designer.css").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login").permitAll()
                .and().logout();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                .userSearchFilter(userSearchFilter)
                // .groupSearchBase(groupSearchBase)
                .contextSource()
                .url(ldapUrl)
                .managerDn(managerDn)
                .managerPassword(managerPassword)
//                .and()
//                .passwordCompare()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .passwordAttribute(passwordAttribute)
                ;
    }
}
