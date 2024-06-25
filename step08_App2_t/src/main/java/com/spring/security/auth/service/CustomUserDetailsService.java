package com.spring.security.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.user.domain.entity.User;
import com.spring.security.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(""));
        
        return new CustomUserDetails(user);
    }
    
    // 필요시 추가
    public UserDetails loadUserByUserId(Long userId) throws IllegalArgumentException {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException(""));
        
        return new CustomUserDetails(user);
    }
    
    // 필요시 추가
    public UserDetails loadUserByEmail(String email) throws IllegalArgumentException {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException(""));
        
        return new CustomUserDetails(user);
    }
}