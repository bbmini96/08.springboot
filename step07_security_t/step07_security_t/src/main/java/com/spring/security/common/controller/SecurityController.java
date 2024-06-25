package com.spring.security.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.user.dto.UserDTO;
import com.spring.security.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@RestController
public class SecurityController {
	
	private final UserService userService;

	@GetMapping("/api/home")
	public String home() {
		
		return "home";
	}
	
	@PostMapping("/api/signup")
	public void saveUser(@RequestBody UserDTO userDTO) {
		log.info("[saveUser] - userDTO : {}", userDTO);
		
		userService.saveUser(userDTO);
	}
	
	@PostMapping("/api/login")
	public void loginUserByEmailAndPassword(@RequestBody UserDTO userDTO) {
		log.info("[saveUser] - userDTO : {}", userDTO);
		
		userService.loginUserByEmailAndPassword(userDTO);
	}
	
	
}
