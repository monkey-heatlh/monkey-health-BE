package com.monkey_company.monkey_health.domain.auth.dto.response;

import com.monkey_company.monkey_health.global.security.jwt.dto.JwtToken;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private String message;
    private JwtToken token;

    public LoginResponse(String message, JwtToken token) {
        this.message = message;
        this.token = token;
    }
}
