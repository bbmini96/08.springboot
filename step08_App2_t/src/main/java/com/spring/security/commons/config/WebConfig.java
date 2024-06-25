package com.spring.security.commons.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	  @Override 
	  public void addCorsMappings(CorsRegistry registry) { 
	    registry
	      .addMapping("/**") 
	      .allowedOrigins("http://localhost:3000") 
	      .allowedMethods("*") 
	      .allowCredentials(true); 	// 쿠키나 데이터값을 사용하기 위해서 반드시 있어야한다
	  } 
}