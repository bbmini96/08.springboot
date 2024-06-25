package com.spring.security.user.dto;

import com.spring.security.user.domain.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String role;
    private String email;
    private String contact;
    private String username;
    
    public UserResponseDto(User entity) {
    	this.id = entity.getId();
        this.email = entity.getEmail();
        this.contact = entity.getContact();
        this.username = entity.getUsername();
        this.role = entity.getRole().name();
    }
}
