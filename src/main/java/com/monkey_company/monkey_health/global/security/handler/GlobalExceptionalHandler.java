package com.monkey_company.monkey_health.global.security.handler;

import com.monkey_company.monkey_health.domain.member.dto.response.MemberRegisterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MemberRegisterResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        MemberRegisterResponse response = MemberRegisterResponse.builder()
                .response(e.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }
}
