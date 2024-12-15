package com.monkey_company.monkey_health.global.config.properties;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan(basePackages = {
        "com.monkey_company.monkey_health.global.security.jwt.properties"})
public class PropertiesScanConfig {
}
