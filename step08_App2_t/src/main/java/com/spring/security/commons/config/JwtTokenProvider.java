package com.spring.security.commons.config;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.spring.security.auth.service.CustomUserDetails;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String jwtSecretKey;
    
    @Value("${jwt.accessTokenExpirationTime}")
    private Long jwtAccessTokenExpirationTime;
    
    @Value("${jwt.refreshTokenExpirationTime}")
    private Long jwtRefreshTokenExpirationTime;
    
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    public String generateAccessToken(Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Date expiryDate = new Date(new Date().getTime() + jwtAccessTokenExpirationTime);
        
        return Jwts.builder()
//        		.setHeader(	)	// 알고리즘 정보값
                .setSubject(customUserDetails.getUsername())	// 사용용도
                .claim("user-id", customUserDetails.getId())	// 페이로드에 사용되는것
                .claim("user-email", customUserDetails.getEmail())	// 페이로드에 사용되는것
                .setIssuedAt(new Date())	// 언제 발행되었는디
                .setExpiration(expiryDate)	// !언제 종료되는지!
                .signWith(getSigningKey())	// 서버에서 제공하는 비밀 키 암호화
                .compact();
    }
    
    public String generateRefreshToken(Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Date expiryDate = new Date(new Date().getTime() + jwtRefreshTokenExpirationTime);
        
//        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        
        return Jwts.builder()
                .setSubject(customUserDetails.getUsername())
                .claim("user-id", customUserDetails.getId())
                .claim("user-email", customUserDetails.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(getSigningKey())
//                .signWith(key)
                .compact();
    }
    
    public Long getUserIdFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("user-id", Long.class);
    }

    public String getUsernameFromToken(String token) {
        return Jwts
        		.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getUserEmailFromToken(String token) {
        return Jwts.parserBuilder()
        		.setSigningKey(getSigningKey())
        		.build()
                .parseClaimsJws(token)
                .getBody()
                .get("user-email", String.class);
    }
    
    public Date getExpirationFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }
    
    public Boolean validateToken(String token) {
        try {
            Jwts
            	.parserBuilder()
            	.setSigningKey(getSigningKey())
            	.build()
            	.parseClaimsJws(token);
            
            return true;
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty.");
        }
        return false;
    }
}