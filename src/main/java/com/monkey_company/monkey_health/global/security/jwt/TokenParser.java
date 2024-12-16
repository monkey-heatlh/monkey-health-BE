package com.monkey_company.monkey_health.global.security.jwt;

import com.monkey_company.monkey_health.global.security.auth.CustomUserDetailsService;
import com.monkey_company.monkey_health.global.security.jwt.properties.JwtEnvironment;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import static com.monkey_company.monkey_health.global.security.jwt.TokenGenerator.getTokenBody;

@Component
@RequiredArgsConstructor
public class TokenParser {

    private final JwtEnvironment jwtEvt;

    private final CustomUserDetailsService customUserDetailsService;

    public UsernamePasswordAuthenticationToken authenticationToken(String accessToken) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(getAccessTokenSubject(accessToken));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getAccessTokenSubject(String accessToken) {
        return getTokenBody(accessToken, Keys.hmacShaKeyFor(jwtEvt.accessSecret().getBytes())).getSubject();
    }
}
