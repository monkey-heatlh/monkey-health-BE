package com.monkey_company.monkey_health.global.security.auth.dto.response;

import lombok.Getter;

@Getter
public class RegisterResponse {

    private String response;

    public RegisterResponse(String response) {
        this.response = response;
    }
}