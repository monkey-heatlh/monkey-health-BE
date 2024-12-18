package com.monkey_company.monkey_health.domain.auth.service;

import com.monkey_company.monkey_health.domain.auth.dto.request.SignUpRequest;
import com.monkey_company.monkey_health.domain.auth.dto.response.SignUpResponse;

public interface SignUpService {
    SignUpResponse signUp(SignUpRequest request);
}
