package com.monkey_company.monkey_health.domain.auth.controller;

import com.monkey_company.monkey_health.domain.auth.dto.request.EmailVerificationRequest;
import com.monkey_company.monkey_health.domain.auth.dto.request.EmailVerifyCodeRequest;
import com.monkey_company.monkey_health.domain.auth.dto.request.LoginRequest;
import com.monkey_company.monkey_health.domain.auth.dto.request.SignUpRequest;
import com.monkey_company.monkey_health.domain.auth.dto.response.*;
import com.monkey_company.monkey_health.domain.auth.service.LoginService;
import com.monkey_company.monkey_health.domain.auth.service.ReissueTokenService;
import com.monkey_company.monkey_health.domain.auth.service.VerifyCodeService;
import com.monkey_company.monkey_health.domain.auth.service.impl.SendMailServiceImpl;
import com.monkey_company.monkey_health.domain.auth.service.impl.SignUpServiceImpl;
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


    @PostMapping("/send-code")
    public EmailverificationResponse sendCode(@RequestBody EmailVerificationRequest request) {
        return sendMailService.sendMail(request.getEmail());
    }

    @PostMapping("/verify-code")
    public EmailVerifyCodeResponse verifyCode(@RequestBody EmailVerifyCodeRequest request) {
        return verifyCodeService.verifyCode(request.getEmail(), request.getCode());
    }

    @PostMapping("/signup")
    public SignUpResponse register(@RequestBody SignUpRequest request) {
        return signUpService.signUp(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return loginService.login(request);
    }

    @PostMapping("/reissue")
    public ReissueResponse reissueToken(@RequestHeader("refresh") String refreshToken) {
        return reissueTokenService.execute(refreshToken);
    }
}
