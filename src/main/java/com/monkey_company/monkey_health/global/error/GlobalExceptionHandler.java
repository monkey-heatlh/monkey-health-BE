package com.monkey_company.monkey_health.global.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@EnableWebMvc
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final MethodValidationPostProcessor methodValidationPostProcessor;

    public GlobalExceptionHandler(MethodValidationPostProcessor methodValidationPostProcessor) {
        this.methodValidationPostProcessor = methodValidationPostProcessor;
    }

    @ExceptionHandler(GlobalException.class)
    private ResponseEntity<ErrorResponse> expectedException(GlobalException ex) {
        log.warn("ExpectedException : {} ", ex.getMessage());
        log.trace("ExpectedException Details : ", ex);
        return ResponseEntity.status(ex.getHttpStatus().value()).body(ErrorResponse.of(ex));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageConversionException.class})
    public ResponseEntity<ErrorResponse> validationException(MethodArgumentNotValidException ex) {
        log.warn("Validation Failed : {}", ex.getMessage());
        log.trace("Validation Failed Details : ", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(new ErrorResponse(methodArgumentNotValidExceptionToJson(ex)));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> unExpectedException(RuntimeException ex) {
        log.error("UnException Occur : ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(new ErrorResponse("internal server error has occurred"));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> noHandlerFoundException(NoHandlerFoundException ex) {
        log.warn("Not Found Endpoint : {}", ex.getMessage());
        log.trace("Not Found Endpoint Details : ", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    private static String methodArgumentNotValidExceptionToJson(MethodArgumentNotValidException ex) {
        Map<String, Object> globalResults = new HashMap<>();
        Map<String, String> fieldResults = new HashMap<>();

        ex.getBindingResult().getGlobalErrors().forEach(error -> {
            globalResults.put(ex.getBindingResult().getObjectName(), error.getDefaultMessage());
        });
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            fieldResults.put(error.getField(), error.getDefaultMessage());
        });
        globalResults.put(ex.getBindingResult().getObjectName(), fieldResults);

        return new JSONObject(globalResults).toString().replace("\"", "'");
    }
}
