package com.monkey_company.monkey_health.domain.auth.service;

import com.monkey_company.monkey_health.domain.auth.dto.response.EmailverificationResponse;

public interface SendMailService {
    EmailverificationResponse sendMail(String email);
}
