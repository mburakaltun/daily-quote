package com.mba.dailyquote.common.service;

import com.mba.dailyquote.authorization.model.enums.AuthorizationRole;
import com.mba.dailyquote.common.properties.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Service
public class JwtService {

    private final JwtProperties jwtProperties;

    public String generateToken(String email, AuthorizationRole role) {
        Date now = new Date();
        LocalDateTime expirationLocalDateTime = LocalDateTime.now().plusHours(role.getExpirationTimeInHours());
        Date expirationDate = Date.from(expirationLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(getSigningKey())
                .compact();
    }

    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        if (token == null || token.trim().isEmpty()) {
            return null;
        }

        return extractAllClaims(token).getSubject();
    }

    private boolean isTokenExpired(String token) {
        try {
            return extractAllClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            log.error("Error while checking token expiration", e);
            return true;
        }
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes());
    }
}
