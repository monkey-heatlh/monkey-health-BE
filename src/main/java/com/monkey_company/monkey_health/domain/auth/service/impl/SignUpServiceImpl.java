package com.monkey_company.monkey_health.domain.auth.service.impl;

import com.monkey_company.monkey_health.domain.auth.dto.request.SignUpRequest;
import com.monkey_company.monkey_health.domain.auth.dto.response.SignUpResponse;
import com.monkey_company.monkey_health.domain.auth.service.SignUpService;
import com.monkey_company.monkey_health.domain.member.entity.Member;
import com.monkey_company.monkey_health.domain.member.repository.MemberRepository;
import com.monkey_company.monkey_health.global.error.GlobalException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {

    private final RedisTemplate<String, String> redisTemplate;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public SignUpResponse signUp(@Valid SignUpRequest request) {
        checkEmailVerified(request);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Member newMember = createMember(request);
        memberRepository.save(newMember);
        return new SignUpResponse("회원가입이 완료되었습니다.");

    }

    private void checkEmailVerified(SignUpRequest request) {
        String emailVerified = redisTemplate.opsForValue().get("email:verified:" + request.getEmail());
        if (!"true".equals(emailVerified)) {
            throw new GlobalException("이메일 인증이 완료되지 않았습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    private Member createMember(SignUpRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        return new Member(
                request.getEmail(),
                encodedPassword
        );
    }

}
