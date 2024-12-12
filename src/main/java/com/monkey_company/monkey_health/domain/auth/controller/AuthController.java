package com.monkey_company.monkey_health.domain.auth.controller;


import com.monkey_company.monkey_health.domain.auth.dto.request.AuthRequest;
import com.monkey_company.monkey_health.domain.auth.dto.response.LoginResponse;
import com.monkey_company.monkey_health.domain.auth.dto.response.RegisterResponse;
import com.monkey_company.monkey_health.domain.auth.service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public LoginResponse login(@RequestBody AuthRequest request, HttpSession session) {
        return authService.login(request, session);
    }


}
