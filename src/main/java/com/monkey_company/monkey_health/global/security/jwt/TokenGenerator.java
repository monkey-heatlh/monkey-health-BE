package com.monkey_company.monkey_health.global.security.jwt;

import com.monkey_company.monkey_health.global.security.jwt.dto.Token;
import com.monkey_company.monkey_health.global.security.jwt.properties.JwtEnvironment;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenGenerator {

    private final JwtEnvironment jwtEnv;

    private final String TOKEN_TYPE = "tokenType";
    private final String ACCESS_TOKEN = "accessToken";
    private final String REFRESH_TOKEN = "refreshToken";

    public Token generateToken(String email) {
        return Token.builder()
                .accessToken(generateAccessToken(email))
                .refreshToken(generateRefreshToken(email))
                .accessTokenExp(jwtEnv.accessExp())
                .refreshTokenExp(jwtEnv.refreshExp())
                .build();
    }

    public String getUserIdFromRefreshToken(String token) {
        return getRefreshTokenSubject(token);
    }

    private String generateAccessToken(String email) {
        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(jwtEnv.accessSecret().getBytes()), SignatureAlgorithm.HS256)
                .setSubject(email)
                .claim(TOKEN_TYPE, ACCESS_TOKEN)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtEnv.accessExp() * 1000L))
                .compact();
    }

    private String generateRefreshToken(String email) {
        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(jwtEnv.refreshSecret().getBytes()), SignatureAlgorithm.HS256)
                .setSubject(email)
                .claim(TOKEN_TYPE, REFRESH_TOKEN)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtEnv.refreshExp() * 1000L))
                .compact();
    }

    private String getRefreshTokenSubject(String refreshToken) {
        return getTokenBody(refreshToken, Keys.hmacShaKeyFor(jwtEnv.refreshSecret().getBytes())).getSubject();
    }

    public static Claims getTokenBody(String token, Key secret) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
