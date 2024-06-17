package com.spring.aop.common.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

//@WebFilter(urlPatterns = {"/filter-test"})
public class APISecondFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("APISecondFilter 필터 init()");
    }
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("APISecondFilter 필터 전처리");
		
		chain.doFilter(request, response);
		
		System.out.println("APISecondFilter 필터 후처리");
		
	}
	
	@Override
	public void destroy() {
		System.out.println("APISecondFilter 필터 destroy()");
    }

}
