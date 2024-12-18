package com.monkey_company.monkey_health.domain.auth.service;

import com.monkey_company.monkey_health.domain.auth.dto.response.EmailVerifyCodeResponse;
import com.monkey_company.monkey_health.domain.auth.dto.response.EmailverificationResponse;
import com.monkey_company.monkey_health.global.error.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class VerifyCodeService{

    private final RedisTemplate<String, String> redisTemplate;

    public EmailVerifyCodeResponse verifyCode(String email, String code) {
        String storedCode = redisTemplate.opsForValue().get(email);
        if (storedCode == null || !storedCode.equals(code)) {
            throw new GlobalException("인증 번호가 올바르지 않거나 만료되었습니다.", HttpStatus.BAD_REQUEST);
        }
        redisTemplate.opsForValue().set("email:verified:" + email, "true", 3, TimeUnit.MINUTES);
        redisTemplate.delete(email);
        return new EmailVerifyCodeResponse("인증 번호가 확인되었습니다");
    }

}
