package com.monkey_company.monkey_health.domain.auth.service.impl;

import com.monkey_company.monkey_health.domain.auth.dto.response.LogoutResponse;
import com.monkey_company.monkey_health.domain.auth.entity.RefreshToken;
import com.monkey_company.monkey_health.domain.auth.repository.RefreshTokenRepository;
import com.monkey_company.monkey_health.domain.auth.service.LogoutService;
import com.monkey_company.monkey_health.global.security.jwt.TokenParser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class LogoutServiceImpl implements LogoutService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenParser tokenParser;
    private final RedisTemplate redisTemplate;

    @Override
    public LogoutResponse logout(String token) {
        String email = tokenParser.getEmailFromToken(token);
        Optional<RefreshToken> byEmail = refreshTokenRepository.findTokenByEmail(email);
        if (byEmail.get() != null) {
            refreshTokenRepository.deleteByEmail(email);
        }

        Long expiration = tokenParser.getExpirationFromToken(token);
        redisTemplate.opsForValue().set(token, "logout", expiration, TimeUnit.MILLISECONDS);

        return new LogoutResponse("로그아웃 되었습니다");
    }

}
