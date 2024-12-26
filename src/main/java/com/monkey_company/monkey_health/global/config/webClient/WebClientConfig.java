package com.monkey_company.monkey_health.global.config.webClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${WEB_CLIENT_IP}")
    private String baseURL;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(baseURL)
                .build();
    }
}
