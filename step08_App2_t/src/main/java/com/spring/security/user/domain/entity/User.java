package com.spring.security.user.domain.entity;

import com.spring.security.auth.domain.entity.Auth;
import com.spring.security.commons.util.BaseTime;
import com.spring.security.user.dto.UserRequestDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class User extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 50, nullable = true, unique = true)
    private String email;
    
    @Column(length = 50, nullable = true, unique = true)
    private String contact;
    
    @Column(length = 50, nullable = false, unique = true)
    private String username;
    
    @Column(length = 100, nullable = false)
    private String password;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Auth auth;

    @Builder
    public User(String email, String contact, String username, String password, Role role) {
    	this.role = role;
        this.email = email;
        this.contact = contact;
        this.username = username;
        this.password = password;
    }

	public void update(UserRequestDto requestDto) {
    	this.role = requestDto.getRole();
        this.email = requestDto.getEmail();
        this.contact = requestDto.getContact();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
	}
}