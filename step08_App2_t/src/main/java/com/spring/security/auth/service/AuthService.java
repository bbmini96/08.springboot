package com.spring.security.auth.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.security.auth.domain.entity.Auth;
import com.spring.security.auth.domain.repository.AuthRepository;
import com.spring.security.auth.dto.AuthRequestDto;
import com.spring.security.auth.dto.AuthResponseDto;
import com.spring.security.commons.config.JwtTokenProvider;
import com.spring.security.user.domain.entity.Role;
import com.spring.security.user.domain.entity.User;
import com.spring.security.user.domain.repository.UserRepository;
import com.spring.security.user.dto.UserRequestDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public AuthResponseDto login(AuthRequestDto requestDto) {
        User user = userRepository.findByUsername(requestDto.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException(""));
                
        
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("");
        }
        
        String accessToken = jwtTokenProvider.generateAccessToken(
                new UsernamePasswordAuthenticationToken(new CustomUserDetails(user), user.getPassword()));
        
        String refreshToken = jwtTokenProvider.generateRefreshToken(
                new UsernamePasswordAuthenticationToken(new CustomUserDetails(user), user.getPassword()));

        if (this.authRepository.existsByUser(user)) {
            user.getAuth().updateAccessToken(accessToken);
            user.getAuth().updateRefreshToken(refreshToken);
            return new AuthResponseDto(user.getAuth());
        }
        
        Auth auth = this.authRepository.save(Auth.builder()
                        .user(user)
                        .tokenType("Bearer")
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build());
        
        return new AuthResponseDto(auth);
    }

    @Transactional
    public void signup(UserRequestDto requestDto) {
        requestDto.setRole(Role.ROLE_USER);
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        
        userRepository.save(requestDto.toEntity());
    }

    @Transactional
    public String refreshToken(String refreshToken) throws IllegalArgumentException, Exception {
        if (this.jwtTokenProvider.validateToken(refreshToken)) {
            Auth auth = authRepository.findByRefreshToken(refreshToken).orElseThrow(
                    () -> new IllegalArgumentException(""));

            String newAccessToken = jwtTokenProvider.generateAccessToken(
                    new UsernamePasswordAuthenticationToken(
                            new CustomUserDetails(auth.getUser()), auth.getUser().getPassword()));
            
            auth.updateAccessToken(newAccessToken);
            return newAccessToken;
        }

        return null;
    }
}