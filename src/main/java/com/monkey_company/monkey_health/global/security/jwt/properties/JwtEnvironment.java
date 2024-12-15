package com.monkey_company.monkey_health.global.security.jwt.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtEnvironment (
    String accessSecret,
    String refreshSecret,
    int accessExp,
    int refreshExp
) {
}
