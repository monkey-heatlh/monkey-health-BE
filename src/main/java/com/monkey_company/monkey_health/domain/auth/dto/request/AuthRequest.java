package com.monkey_company.monkey_health.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class AuthRequest {

    @Pattern(regexp="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+[.][a-zA-Z]{2,3}$", message="이메일 주소 양식을 확인해주세요")
    @NotBlank private String email;
    @NotBlank private String password;

    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
