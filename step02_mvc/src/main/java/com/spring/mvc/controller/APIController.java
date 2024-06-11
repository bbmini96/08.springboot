package com.spring.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mvc.model.Developer;

@RestController
public class APIController {
	
	@GetMapping("/api/dev/{no}")
	public Developer getAPIDev(@PathVariable int no) {
		
		Developer dev = new Developer("dev", no);
		
		return dev;
	}
	
}
