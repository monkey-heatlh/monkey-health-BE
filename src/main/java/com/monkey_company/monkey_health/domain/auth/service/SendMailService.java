package com.monkey_company.monkey_health.domain.auth.service;

import com.monkey_company.monkey_health.domain.auth.dto.response.EmailverificationResponse;
import jakarta.mail.internet.MimeMessage;

public interface SendMailService {
    EmailverificationResponse sendMail(String email);
}
