package com.globomatics.bike.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;

/**
 * @Configuration tells it, Spring Boot, that this is a configuration class and should be
 * run when the container is started so that any configuration can occur at that time
 *
 * @EnableWebSecurity is another Spring Boot tab that is saying, go ahead and enable
 * security for the applicationand anything defined in the class down below is how
 * we'll secure the application
 */

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Value(value = "${auth0.apiAudience}")
    private String apiAudience;
    @Value(value = "${auth0.issuer}")
    private String issuer;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtWebSecurityConfigurer
                .forRS256(apiAudience, issuer)
                .configure(http)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/bikes").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/bikes").hasAuthority("view:registrations")
                .antMatchers(HttpMethod.GET, "/api/v1/bikes/**").hasAuthority("view:registration")
                .anyRequest().authenticated();
    }

}