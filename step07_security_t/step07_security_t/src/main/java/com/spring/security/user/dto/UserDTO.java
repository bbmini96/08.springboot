package com.spring.security.user.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.security.user.entity.Role;
import com.spring.security.user.entity.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
public class UserDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
    
    @Builder
	public UserDTO(String firstname, String lastname, String email, String password, Role role) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.role = role;
	}
    
    public static User toEntity(UserDTO userDTO, PasswordEncoder passwordEncoder) {
    	return User.builder()
    				.firstname(userDTO.getFirstname())
    				.lastname(userDTO.getLastname())
    				.email(userDTO.getEmail())
    				.password(passwordEncoder.encode(userDTO.getPassword()))
    				.role(userDTO.getRole())
    				.build();
    }
    
}
