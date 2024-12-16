package com.monkey_company.monkey_health.domain.auth.controller;


import com.monkey_company.monkey_health.domain.auth.dto.request.AuthRequest;
import com.monkey_company.monkey_health.domain.auth.dto.response.LoginResponse;
import com.monkey_company.monkey_health.domain.auth.dto.response.RegisterResponse;
import com.monkey_company.monkey_health.domain.auth.service.AuthService;
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

    // 회원 가입
    @PostMapping("/register")
    public RegisterResponse registerMember(@RequestBody AuthRequest request) {
        authService.register(request);
        return new RegisterResponse("회원가입 되었습니다");
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }

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
