package com.spring.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AOPController {

	
	@GetMapping("/filter-test")
	public String testFilter() {
		
		System.out.println("---AOP Controller---");
		
		return "filter-test";
	}
	
	
	
}
