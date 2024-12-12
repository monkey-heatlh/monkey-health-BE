package com.monkey_company.monkey_health.global.exception;

import com.monkey_company.monkey_health.global.exception.error.ExpectedException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ExpectedException을 처리하는 메소드
    @ExceptionHandler(ExpectedException.class)
    public ResponseEntity<ErrorResponse> handleExpectedException(ExpectedException ex) {
        // ErrorResponse 객체에 예외 정보 및 상태 코드 담기
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getStatusCode());

        // 클라이언트에 응답 반환 (상태 코드와 메시지 포함)
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

    @Getter
    @Setter
    public static class ErrorResponse {
        private String message;
        private HttpStatus status;

        public ErrorResponse(String message, HttpStatus status) {
            this.message = message;
            this.status = status;
        }
    }
}
