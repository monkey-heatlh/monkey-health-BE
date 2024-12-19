package com.monkey_company.monkey_health.domain.auth.service;

import com.monkey_company.monkey_health.domain.auth.dto.response.ReissueResponse;
import com.monkey_company.monkey_health.domain.auth.entity.RefreshToken;
import com.monkey_company.monkey_health.domain.auth.repository.RefreshTokenRepository;
import com.monkey_company.monkey_health.domain.member.repository.MemberRepository;
import com.monkey_company.monkey_health.global.error.GlobalException;
import com.monkey_company.monkey_health.global.security.jwt.TokenGenerator;
import com.monkey_company.monkey_health.global.security.jwt.dto.JwtToken;
import com.monkey_company.monkey_health.global.security.jwt.properties.JwtEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.monkey_company.monkey_health.global.filter.JwtRequestFilter.BEARER_PREFIX;

@Service
@RequiredArgsConstructor
public class ReissueTokenService {

    private final JwtEnvironment jwtEnv;
    private final TokenGenerator tokenGenerator;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public ReissueResponse execute(String token) {
        isNotNullRefreshToken(token);

        // 리프레시 토큰에서 프리픽스 제거
        String removePrefixToken = token.replaceFirst(BEARER_PREFIX, "").trim();
        RefreshToken refreshToken = refreshTokenRepository.findByToken(removePrefixToken)
                .orElseThrow(() -> new GlobalException("존재하지 않는 refresh token 입니다.", HttpStatus.NOT_FOUND));

        // Redis TTL을 사용하여 만료 체크는 생략
        String email = tokenGenerator.getUserIdFromRefreshToken(refreshToken.getToken());
        isExistsUser(email); // 이메일로 사용자 확인

        // 기존 리프레시 토큰 무효화
        invalidateOldRefreshToken(email);

        // 새로운 액세스 토큰과 리프레시 토큰 생성
        JwtToken jwtToken = tokenGenerator.generateToken(email);
        saveNewRefreshToken(jwtToken.getRefreshToken(), email); // 새로운 리프레시 토큰 저장

        // JwtToken을 LoginResponse로 변환하여 반환
        return new ReissueResponse(jwtToken.getAccessToken(), jwtToken.getRefreshToken());
    }

    private void isNotNullRefreshToken(String token) {
        if (token == null)
            throw new GlobalException("refresh token을 요청 헤더에 포함시켜 주세요.", HttpStatus.BAD_REQUEST);
    }

    private void isExistsUser(String email) {
        if (!memberRepository.existsByEmail(email)) // 이메일로 사용자 존재 여부 확인
            throw new GlobalException("유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
    }

    private void saveNewRefreshToken(String token, String email) {
        RefreshToken refreshToken = RefreshToken.builder()
                .email(email)
                .token(token)
                .ttl(jwtEnv.refreshExp()) // TTL을 설정
                .build();

        refreshTokenRepository.save(refreshToken);
    }

    private void invalidateOldRefreshToken(String email) {
        refreshTokenRepository.deleteByEmail(email); // 이메일로 기존 리프레시 토큰 무효화
    }
}
