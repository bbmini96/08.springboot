package com.spring.security.user.service;

import java.util.NoSuchElementException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.security.user.dto.UserDTO;
import com.spring.security.user.entity.User;
import com.spring.security.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
	
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	
	@Transactional
	public UserDetails loadUserByEmail(String email) {
		log.info("[loadUserByEmail] - email : {}", email);
		
		return userRepository.findByEmail(email).orElseThrow(() ->  new NoSuchElementException("유저 존재 X"));
	}
	
	@Transactional
	public void saveUser(UserDTO userDTO) {
		log.info("[saveUser] - userDTO : {}", userDTO);
		
		User user = UserDTO.toEntity(userDTO, passwordEncoder);
		
		userRepository.save(user);
		
	}
	
	@Transactional
	public void loginUserByEmailAndPassword(UserDTO userDTO) {
		
		// logic
		
	}
	
}
