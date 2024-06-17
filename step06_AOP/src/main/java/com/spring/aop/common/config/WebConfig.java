package com.spring.aop.common.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.spring.aop.common.filter.APIFirsrtFilter;
import com.spring.aop.common.filter.APISecondFilter;
import com.spring.aop.common.intercepter.APIFirstInterceptor;

import jakarta.servlet.Filter;


@Configuration
public class WebConfig implements WebMvcConfigurer {
	 
	@Bean
	public FilterRegistrationBean filterBean(){
		FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>(new APIFirsrtFilter());
		registrationBean.addUrlPatterns("/filter-test");
		registrationBean.setOrder(1); // order 낮은 숫자 우선순위 높음
		
		return registrationBean;
	}
	
	@Bean
	public FilterRegistrationBean secondFilterBean() {
		FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>(new APISecondFilter());
		registrationBean.addUrlPatterns("/filter-test");
		registrationBean.setOrder(2);
		
		return registrationBean;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new APIFirstInterceptor())
				.order(1)
				.addPathPatterns("/filter-test")
				.excludePathPatterns("/static/**");
		
	}
	
}
