package com.spring.security.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.commons.config.JwtTokenProvider;
import com.spring.security.user.dto.UserRequestDto;
import com.spring.security.user.dto.UserResponseDto;
import com.spring.security.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/api/v1/user")
    public ResponseEntity<?> findUser(@RequestHeader("Authorization") String accessToken) {
    	Long id = jwtTokenProvider.getUserIdFromToken(accessToken.substring(7));
        UserResponseDto userResponseDto = userService.findById(id);
        
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @PutMapping("/api/v1/user")
    public ResponseEntity<?> updateUser(@RequestHeader("Authorization") String accessToken, 
                                        @RequestBody UserRequestDto requestDto) {
        Long id = this.jwtTokenProvider.getUserIdFromToken(accessToken.substring(7));
        
        userService.update(id, requestDto);
        
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    
    @DeleteMapping("/api/v1/user")
    public ResponseEntity<?> deleteUser(@RequestHeader("Authorization") String accessToken) {
        Long id = this.jwtTokenProvider.getUserIdFromToken(accessToken.substring(7));
        this.userService.delete(id);
        
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
