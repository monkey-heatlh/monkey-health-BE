package com.monkey_company.monkey_health.global.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/**").permitAll()  // 로그인 API는 허용
                        .anyRequest().authenticated()  // 나머지 요청은 인증된 사용자만 접근 가능
                )
                .formLogin(form -> form
                        .loginProcessingUrl("/auth/login")  // 로그인 요청 처리 URL 설정
                        .successHandler((request, response, authentication) -> {
                            // 로그인 성공 시 사용자 정보 반환
                            response.setContentType("application/json");
                            response.getWriter().write("{\"message\": \"로그인 성공\"}");
                            response.setStatus(HttpServletResponse.SC_OK);
                        })
                        .failureHandler((request, response, exception) -> {
                            // 로그인 실패 시 에러 메시지 반환
                            response.setContentType("application/json");
                            response.getWriter().write("{\"message\": \"로그인 실패\"}");
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        })
                )
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setContentType("application/json");
                            response.getWriter().write("{\"message\": \"로그아웃 성공\"}");
                            response.setStatus(HttpServletResponse.SC_OK);
                        })
                );

        return http.build();
    }



    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
