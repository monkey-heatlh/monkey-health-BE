package com.monkey_company.monkey_health.global.security.jwt;

import com.monkey_company.monkey_health.global.error.GlobalException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${jwt.accessSecret}")
    private String secretKey;

    public String getEmailFromToken(String token) {
        try {
            token = token.trim();
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (Exception e) {
            throw new GlobalException("유효하지 않은 JWT 토큰입니다.", HttpStatus.NOT_FOUND);
        }
    }
}
