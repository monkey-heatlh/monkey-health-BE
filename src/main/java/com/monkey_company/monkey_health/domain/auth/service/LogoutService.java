package com.monkey_company.monkey_health.domain.auth.service;

import com.monkey_company.monkey_health.domain.auth.dto.response.LogoutResponse;

public interface LogoutService {
    LogoutResponse logout(String token);
}
