package com.monkey_company.monkey_health.global.security.auth.controller;


import com.monkey_company.monkey_health.domain.member.dto.request.MemberRequest;
import com.monkey_company.monkey_health.domain.member.dto.response.MemberRegisterResponse;
import com.monkey_company.monkey_health.domain.member.entity.Member;
import com.monkey_company.monkey_health.global.security.auth.dto.request.AuthRequest;
import com.monkey_company.monkey_health.global.security.auth.dto.response.LoginResponse;
import com.monkey_company.monkey_health.global.security.auth.dto.response.RegisterResponse;
import com.monkey_company.monkey_health.global.security.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 회원 가입
    @PostMapping("/register")
    public RegisterResponse registerMember(@RequestBody AuthRequest request) {
        authService.register(request);
        return new RegisterResponse("회원가입 되었습니다");
    }

    // 로그인
    @PostMapping("/login")
    public LoginResponse loginResponse(@RequestBody AuthRequest request) {
        String token = String.valueOf(authService.login(request));
        return new LoginResponse(token);
    }

}
