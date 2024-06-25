package com.spring.security.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.auth.dto.AuthRequestDto;
import com.spring.security.auth.dto.AuthResponseDto;
import com.spring.security.auth.service.AuthService;
import com.spring.security.user.dto.UserRequestDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthRestController {
    private final AuthService authService;

    @PostMapping("/api/v1/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDto requestDto) {
        AuthResponseDto responseDto = authService.login(requestDto);
        
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/api/v1/auth/signup")
    public ResponseEntity<?> singUp(@RequestBody UserRequestDto requestDto) {
        authService.signup(requestDto);
        
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @GetMapping("/api/v1/auth/refresh")
    public ResponseEntity<?> refreshToken(@RequestHeader("REFRESH_TOKEN") String refreshToken) throws IllegalArgumentException, Exception {
        String newAccessToken = this.authService.refreshToken(refreshToken);
        
        return ResponseEntity.status(HttpStatus.OK).body(newAccessToken);
    }
}