package com.monkey_company.monkey_health.domain.auth.dto.response;

import com.monkey_company.monkey_health.domain.auth.dto.request.EmailVerificationRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailverificationResponse {

    private String message;
}
