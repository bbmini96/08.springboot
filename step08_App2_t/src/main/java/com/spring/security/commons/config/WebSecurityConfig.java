package com.spring.security.commons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.spring.security.commons.filter.JwtTokenFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
        		.csrf(csrf -> csrf.disable())
        		
        		.authorizeHttpRequests(
        			(request) -> request
        							.requestMatchers("/api/v1/admin/**", "/api/v2/admin/**")
        							.hasRole("ADMIN")
        							
        							.requestMatchers("/api/v1/auth/**", "/api/v2/auth/**")
        							.permitAll()
        							
        							.requestMatchers("/api/v1/user/**", "/api/v2/user/**")
        							.authenticated()
        		)
        		
        		.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
        		
        		.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}