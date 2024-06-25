package com.spring.security.user.dto;

import com.spring.security.user.domain.entity.Role;
import com.spring.security.user.domain.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private String email;
    private String contact;
    private String username;
    private String password;
    private Role role;

    public User toEntity() {
        return User.builder()
                .email(this.email)
                .username(this.username)
                .password(this.password)
                .contact(this.contact)
                .role(this.role)
                .build();
    }
}