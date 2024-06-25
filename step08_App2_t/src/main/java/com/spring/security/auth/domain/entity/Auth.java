package com.spring.security.auth.domain.entity;

import com.spring.security.commons.util.BaseTime;
import com.spring.security.user.domain.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Auth extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tokenType;

    @Column(nullable = false)
    private String accessToken;	// 접근 권한

    @Column(nullable = false)
    private String refreshToken;	// 토큰 다시 발행해서 사용할 수 있게 사용

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Auth(User user, String tokenType, String accessToken, String refreshToken) {
        this.user = user;
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

	public void updateAccessToken(String newAccessToken) {
		this.accessToken = newAccessToken;
	}

	public void updateRefreshToken(String refreshToken2) {
		this.refreshToken = refreshToken2;
	}
}
