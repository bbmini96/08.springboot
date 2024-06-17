package com.spring.aop.common.intercepter;

import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class APIFirstInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("APIFirstInterceptor preHandle");
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		System.out.println(handlerMethod.getBean());
		System.out.println(handlerMethod.getMethod());
		System.out.println(handlerMethod.getReturnType());
		
		// false: 컨트롤러 실행 X
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		
		System.out.println("APIFirstInterceptor postHandle");
		System.out.println(modelAndView.getViewName());
		System.out.println(modelAndView.getModel());
		System.out.println(modelAndView.getModel().keySet());
		System.out.println(modelAndView.getModel().values());
		System.out.println(modelAndView.getModel().get("key"));
	} 
	
	// 반환되는 로직 정리(잘 사용하지 않는다)	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		System.out.println("APIFirstInterceptor afterCompletion");

		
		
	}
}
