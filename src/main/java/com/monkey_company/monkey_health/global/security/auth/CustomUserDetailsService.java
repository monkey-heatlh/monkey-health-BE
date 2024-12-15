package com.monkey_company.monkey_health.global.security.auth;

import com.monkey_company.monkey_health.domain.auth.repository.AuthRepository;
import com.monkey_company.monkey_health.domain.member.entity.Member;
import com.monkey_company.monkey_health.global.error.GlobalException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Getter
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = authRepository.findByEmail(email)
                .orElseThrow(() -> new GlobalException("유저를 찾을 수 없습니다. ", HttpStatus.NOT_FOUND));

        return new CustomUserDetails(member);
    }

}
