package com.monkey_company.monkey_health.global.security.auth.service;

import com.monkey_company.monkey_health.domain.member.entity.Member;
import com.monkey_company.monkey_health.global.security.auth.dto.request.AuthRequest;
import com.monkey_company.monkey_health.global.security.auth.dto.response.LoginResponse;
import com.monkey_company.monkey_health.global.security.auth.repository.AuthRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public Member register(AuthRequest request) {
        // 이메일 중복 확인
        if (authRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다");
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

    public LoginResponse login(AuthRequest request) {
        // 이메일을 사용하여 회원 정보 조회
        Member member = authRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 비밀번호 검증
        if (!passwordEncoder.matches(request.getPassword(), request.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        // JWT 토큰 생성
        String token = jwtProvider.createToken(member.getEmail(), member.getRoles());
        return new LoginResponse(token);
    }

}
