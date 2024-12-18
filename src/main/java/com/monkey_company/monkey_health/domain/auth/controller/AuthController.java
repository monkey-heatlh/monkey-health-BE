package com.monkey_company.monkey_health.domain.auth.controller;

import com.monkey_company.monkey_health.domain.auth.dto.request.EmailVerificationRequest;
import com.monkey_company.monkey_health.domain.auth.dto.request.EmailVerifyCodeRequest;
import com.monkey_company.monkey_health.domain.auth.dto.request.LoginRequest;
import com.monkey_company.monkey_health.domain.auth.dto.request.SignUpRequest;
import com.monkey_company.monkey_health.domain.auth.dto.response.EmailVerifyCodeResponse;
import com.monkey_company.monkey_health.domain.auth.dto.response.EmailverificationResponse;
import com.monkey_company.monkey_health.domain.auth.dto.response.LoginResponse;
import com.monkey_company.monkey_health.domain.auth.dto.response.SignUpResponse;
import com.monkey_company.monkey_health.domain.auth.service.ReissueTokenService;
import com.monkey_company.monkey_health.domain.auth.service.LoginService;
import com.monkey_company.monkey_health.domain.auth.service.VerifyCodeService;
import com.monkey_company.monkey_health.domain.auth.service.impl.SendMailServiceImpl;
import com.monkey_company.monkey_health.domain.auth.service.impl.SignUpServiceImpl;
import com.monkey_company.monkey_health.global.security.jwt.dto.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final VerifyCodeService verifyCodeService;
    private final ReissueTokenService reissueTokenService;
    private final SendMailServiceImpl sendMailService;
    private final SignUpServiceImpl signUpService;
    private final LoginService loginService;


    // 이메일 인증 번호 발송
    @PostMapping("/send-code")
    public EmailverificationResponse sendCode(@RequestBody EmailVerificationRequest request) {
        return sendMailService.sendMail(request.getEmail());
    }

    // 이메일 인증 번호 검증
    @PostMapping("/verify-code")
    public EmailVerifyCodeResponse verifyCode(@RequestBody EmailVerifyCodeRequest request) {
        return verifyCodeService.verifyCode(request.getEmail(), request.getCode());
    }

    // 회원가입
    @PostMapping("/signup")
    public SignUpResponse register(@RequestBody SignUpRequest request) {
        return signUpService.signUp(request);
    }

    // 로그인
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return loginService.login(request);
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
