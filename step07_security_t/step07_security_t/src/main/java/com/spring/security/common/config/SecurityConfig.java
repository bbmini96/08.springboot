package com.spring.security.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests((request) -> request
													.requestMatchers("/login","/api/home").permitAll()	//경로로 갈때는 허용
													.anyRequest().authenticated()
				)
				
				.sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				)
				
				.formLogin((formlogin) -> formlogin
											.loginPage("/login")
											.defaultSuccessUrl("/api/home")	// 로그인 성공하면 어디로 이동할 것인가?
				)
				
				.logout((logout) -> logout
										.logoutSuccessUrl("/api/home")
				)
				
				.build();
	}
	
}
