package com.monkey_company.monkey_health.domain.auth.service;

import com.monkey_company.monkey_health.domain.auth.dto.request.LoginRequest;
import com.monkey_company.monkey_health.domain.auth.dto.response.LoginResponse;
import com.monkey_company.monkey_health.domain.member.entity.Member;
import com.monkey_company.monkey_health.domain.member.repository.MemberRepository;
import com.monkey_company.monkey_health.domain.auth.entity.RefreshToken; // Import 추가
import com.monkey_company.monkey_health.domain.auth.repository.RefreshTokenRepository; // Import 추가
import com.monkey_company.monkey_health.global.error.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.monkey_company.monkey_health.global.security.jwt.TokenGenerator;
import com.monkey_company.monkey_health.global.security.jwt.dto.JwtToken;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    private final TokenGenerator tokenGenerator;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository; // RefreshTokenRepository 주입 추가

    public LoginResponse login(LoginRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    log.error("사용자 이메일로 회원을 찾을 수 없습니다: {}", request.getEmail());
                    return new GlobalException("사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
                });
        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new GlobalException("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
        }

        JwtToken jwtToken = tokenGenerator.generateToken(member.getEmail());

        // 리프레시 토큰 생성
        String refreshToken = tokenGenerator.generateRefreshToken(member.getEmail()); // 리프레시 토큰 생성 메서드 예시

        // 리프레시 토큰 저장
        RefreshToken refreshTokenEntity = RefreshToken.builder()
                .email(member.getEmail())
                .token(refreshToken)
                .ttl(60 * 60 * 24 * 30) // 예: 30일 TTL 설정
                .build();

        refreshTokenRepository.save(refreshTokenEntity);

        return new LoginResponse("로그인 성공", jwtToken);
    }
}
