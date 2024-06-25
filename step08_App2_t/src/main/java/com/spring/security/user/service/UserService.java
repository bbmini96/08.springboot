package com.spring.security.user.service;

import org.springframework.stereotype.Service;

import com.spring.security.user.domain.entity.User;
import com.spring.security.user.domain.repository.UserRepository;
import com.spring.security.user.dto.UserRequestDto;
import com.spring.security.user.dto.UserResponseDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    
    @Transactional
    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(""));
        
        return new UserResponseDto(user);
    }

    @Transactional
    public void update(Long id, UserRequestDto requestDto) {
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(""));
        
        user.update(requestDto);
    }

    @Transactional
    public void delete(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(""));
        
        this.userRepository.delete(user);
    }
}
