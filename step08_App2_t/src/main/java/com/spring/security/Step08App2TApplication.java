package com.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Step08App2TApplication {

	public static void main(String[] args) {
		SpringApplication.run(Step08App2TApplication.class, args);
	}

}
