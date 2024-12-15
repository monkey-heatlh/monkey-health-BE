package com.monkey_company.monkey_health.global.filter;

import com.monkey_company.monkey_health.global.security.jwt.TokenParser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final TokenParser tokenParser;

    public static String AUTHORIZATION_HEADER = "Authorization";
    public static String BEARER_PREFIX = "Bearer";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = request.getHeader(AUTHORIZATION_HEADER);

        if (accessToken != null) {
            accessToken = accessToken.replaceFirst(BEARER_PREFIX, "").trim();

            UsernamePasswordAuthenticationToken authentication = tokenParser.authenticationToken(accessToken);
            SecurityContextHolder.clearContext();
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
