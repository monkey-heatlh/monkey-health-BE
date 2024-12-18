package com.monkey_company.monkey_health.domain.auth.dto.response;

import com.monkey_company.monkey_health.global.security.jwt.dto.JwtToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {

    private String message;
    private JwtToken token;

}