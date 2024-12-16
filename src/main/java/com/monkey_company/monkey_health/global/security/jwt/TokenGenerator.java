package com.monkey_company.monkey_health.global.security.jwt;

import com.monkey_company.monkey_health.global.error.GlobalException;
import com.monkey_company.monkey_health.global.security.jwt.dto.JwtToken;
import com.monkey_company.monkey_health.global.security.jwt.properties.JwtEnvironment;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenGenerator {

    private final JwtEnvironment jwtEnv;
    private final String TOKEN_TYPE = "tokenType";
    private final String ACCESS_TOKEN = "accessToken";
    private final String REFRESH_TOKEN = "refreshToken";

    public JwtToken generateToken(String email) {
        try {
            return JwtToken.builder()
                    .accessToken(generateAccessToken(email))
                    .refreshToken(generateRefreshToken(email))
                    .accessTokenExp(jwtEnv.accessExp())
                    .refreshTokenExp(jwtEnv.refreshExp())
                    .build();
        } catch (Exception e) {
            log.error("Error generating JWT tokens: ", e);
            throw new GlobalException("JWT 생성 오류", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String getUserIdFromRefreshToken(String token) {
        return getRefreshTokenSubject(token);
    }

    private String generateAccessToken(String email) {
        // accessSecret을 가져오고, 길이를 확인
        String accessSecret = jwtEnv.accessSecret();

        // 비밀 키의 길이를 256비트(32바이트) 이상으로 확장
        if (accessSecret.length() < 32) {
            throw new IllegalArgumentException("Access secret must be at least 256 bits.");
        }

        // 비밀 키를 바이트 배열로 변환하고, 안전한 키 생성
        SecretKey key = Keys.hmacShaKeyFor(accessSecret.getBytes());

        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setSubject(email)
                .claim(TOKEN_TYPE, ACCESS_TOKEN)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtEnv.accessExp() * 1000L)) // 초 단위 확인 필요
                .compact();
    }

    private String generateRefreshToken(String email) throws NoSuchAlgorithmException {
        // refreshSecret을 가져오고, 길이를 확인
        String refreshSecret = jwtEnv.refreshSecret();

        // 비밀 키의 길이를 256비트(32바이트) 이상으로 확장
        if (refreshSecret.length() < 32) {
            refreshSecret = new String(MessageDigest.getInstance("SHA-256").digest(refreshSecret.getBytes()));


        }

        // 비밀 키를 바이트 배열로 변환하고, 안전한 키 생성
        SecretKey key = Keys.hmacShaKeyFor(refreshSecret.getBytes());

        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setSubject(email)
                .claim(TOKEN_TYPE, REFRESH_TOKEN)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtEnv.refreshExp() * 1000L)) // 초 단위 확인 필요
                .compact();
    }

    private String getRefreshTokenSubject(String refreshToken) {
        Claims claims = getTokenBody(refreshToken, Keys.hmacShaKeyFor(jwtEnv.refreshSecret().getBytes()));
        return claims.getSubject();
    }

    public static Claims getTokenBody(String token, Key secret) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new GlobalException("JWT 토큰이 만료되었습니다. 새 토큰을 발급받아 주세요.", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            throw new GlobalException("JWT 토큰이 유효하지 않거나 잘못된 서명입니다.", HttpStatus.UNAUTHORIZED);
        }
    }

}