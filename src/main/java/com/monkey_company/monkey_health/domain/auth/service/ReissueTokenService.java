package com.monkey_company.monkey_health.domain.auth.service;

import com.monkey_company.monkey_health.domain.auth.dto.RefreshToken;
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
    public JwtToken execute(String token) {
        isNotNullRefreshToken(token);

        String removePrefixToken = token.replaceFirst(BEARER_PREFIX, "").trim();
        RefreshToken refreshToken = refreshTokenRepository.findByToken(removePrefixToken)
                .orElseThrow(() -> new GlobalException("존재하지 않는 refresh token 입니다.", HttpStatus.NOT_FOUND));

        String userId = tokenGenerator.getUserIdFromRefreshToken(refreshToken.getToken());
        isExistsUser(userId);

        JwtToken jwtToken = tokenGenerator.generateToken(userId);
        saveNewRefreshToken(jwtToken.getRefreshToken(), refreshToken.getUserId());

        return jwtToken;
    }

    private void isExistsUser(String userId) {
        if (!memberRepository.existsById(Long.valueOf(userId))) {
            throw new GlobalException("유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    private void isNotNullRefreshToken(String token) {
        if (token == null) {
            throw new GlobalException("refresh token을 요청 헤더에 포함시켜 주세요.", HttpStatus.BAD_REQUEST);
        }
    }

    private void saveNewRefreshToken(String token, Long userId) {
        RefreshToken refreshToken = RefreshToken.builder()
                .userId(userId)
                .token(token)
                .expirationTime(jwtEnv.refreshExp())
                .build();
    }
}
