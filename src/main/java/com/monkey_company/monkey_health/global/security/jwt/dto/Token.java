package com.monkey_company.monkey_health.global.security.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
public class Token {
    private String accessToken;
    private String refreshToken;
    private int accessTokenExp;
    private int refreshTokenExp;
}
