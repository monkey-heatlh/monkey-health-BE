package com.monkey_company.monkey_health.domain.auth.dto.request;

import com.monkey_company.monkey_health.domain.auth.dto.response.EmailVerifyCodeResponse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailVerifyCodeRequest {

    @NotBlank(message = "이메일은 필수 항목입니다.")
    @Email(message = "유효한 이메일 주소를 입력해주세요.")
    private String email;

    @NotBlank(message = "인증 코드는 필수 항목입니다.")
    private String code;

}
