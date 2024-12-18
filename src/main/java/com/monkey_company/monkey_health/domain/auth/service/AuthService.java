package com.monkey_company.monkey_health.domain.auth.service;

import com.monkey_company.monkey_health.domain.auth.dto.request.LoginRequest;
import com.monkey_company.monkey_health.domain.auth.dto.request.SignupRequest;
import com.monkey_company.monkey_health.domain.auth.dto.response.LoginResponse;
import com.monkey_company.monkey_health.domain.auth.repository.AuthRepository;
import com.monkey_company.monkey_health.domain.member.entity.Member;
import com.monkey_company.monkey_health.global.error.GlobalException;
import com.monkey_company.monkey_health.global.security.jwt.TokenGenerator;
import com.monkey_company.monkey_health.global.security.jwt.dto.JwtToken;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenGenerator tokenGenerator;
    private final MailService mailService;
    private final RedisTemplate<String, String> redisTemplate;

    public void sendVerificationCode(String email){

        mailService.sendMail(email);
    }

    // 이메일 형식 검사 메서드
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }


    // 인증 번호 검증
    public boolean verifyCode(String email, String code) {
        String storedCode = redisTemplate.opsForValue().get(email);

        if (storedCode == null || !storedCode.equals(code)) {
            throw new GlobalException("인증 번호가 올바르지 않거나 만료되었습니다.", HttpStatus.BAD_REQUEST);
        }

        // 인증 완료 상태를 Redis에 저장
        redisTemplate.opsForValue().set("email:verified:" + email, "true", 3, TimeUnit.MINUTES);

        // Redis에서 인증 번호 삭제
        redisTemplate.delete(email);
        return true;
    }

    // 회원 가입
    @Transactional
    public Member register(SignupRequest request) {
        // 이메일 인증 완료 여부 확인
        String emailVerified = redisTemplate.opsForValue().get("email:verified:" + request.getEmail());
        if (!"true".equals(emailVerified)) {
            throw new GlobalException("이메일 인증이 완료되지 않았습니다.", HttpStatus.BAD_REQUEST);
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 회원 생성 및 저장
        Member member = Member.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .build();

        return authRepository.save(member);
    }


    public LoginResponse login(LoginRequest request) {
        Member member = authRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    log.error("사용자 이메일로 회원을 찾을 수 없습니다: {}", request.getEmail());
                    return new GlobalException("사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
                });
        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new GlobalException("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
        }

        JwtToken jwtToken = tokenGenerator.generateToken(member.getEmail());

        // 로그인 성공 응답
        return new LoginResponse("로그인 성공", jwtToken);
    }
}
