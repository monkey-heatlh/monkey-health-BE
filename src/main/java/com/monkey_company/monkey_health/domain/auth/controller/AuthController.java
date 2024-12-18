package com.monkey_company.monkey_health.domain.auth.controller;

import com.monkey_company.monkey_health.domain.auth.dto.request.EmailVerificationRequest;
import com.monkey_company.monkey_health.domain.auth.dto.request.EmailVerifyCodeRequest;
import com.monkey_company.monkey_health.domain.auth.dto.request.LoginRequest;
import com.monkey_company.monkey_health.domain.auth.dto.request.SignupRequest;
import com.monkey_company.monkey_health.domain.auth.dto.response.EmailVerifyCodeResponse;
import com.monkey_company.monkey_health.domain.auth.dto.response.EmailverificationResponse;
import com.monkey_company.monkey_health.domain.auth.dto.response.LoginResponse;
import com.monkey_company.monkey_health.domain.auth.dto.response.SignupResponse;
import com.monkey_company.monkey_health.domain.auth.service.AuthService;
import com.monkey_company.monkey_health.domain.auth.service.MailService;
import com.monkey_company.monkey_health.domain.auth.service.ReissueTokenService;
import com.monkey_company.monkey_health.global.security.jwt.dto.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final ReissueTokenService reissueTokenService;
    private final MailService mailService;

    private int number;

    // 이메일 인증 번호 발송
    @PostMapping("/send-code")
    public EmailverificationResponse sendCode(@RequestBody EmailVerificationRequest request) {
        authService.sendVerificationCode(request.getEmail());
        return new EmailverificationResponse("인증 번호가 발송되었습니다.");
    }

    // 이메일 인증 번호 검증
    @PostMapping("/verify-code")
    public EmailVerifyCodeResponse verifyCode(@RequestBody EmailVerifyCodeRequest request) {
        // 인증 코드 검증 서비스 호출
        authService.verifyCode(request.getEmail(), request.getCode());
        return new EmailVerifyCodeResponse("인증 번호가 확인되었습니다.");
    }

    // 회원가입
    @PostMapping("/signup")
    public SignupResponse register(@RequestBody SignupRequest request) {
        authService.register(request);
        return new  SignupResponse("회원가입이 완료되었습니다.");
    }



    // 로그인
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        // 로그인 서비스 호출
        return authService.login(request);
    }

    // 토큰 재발급
    @PostMapping("/reissue")
    public LoginResponse reissueToken(@RequestHeader("Authorization") String authorizationHeader) {
        // Authorization 헤더에서 Bearer 토큰을 가져옵니다.
        String refreshToken = authorizationHeader.replace("Bearer ", "");

        // refreshToken을 기반으로 새로운 토큰을 재발급합니다.
        JwtToken jwtToken = reissueTokenService.execute(refreshToken);

        // 새 토큰을 반환합니다.
        return new LoginResponse("Token reissued successfully", jwtToken);
    }
}
