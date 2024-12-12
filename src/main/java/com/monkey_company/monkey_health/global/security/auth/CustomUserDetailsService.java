package com.monkey_company.monkey_health.global.security.auth;

import com.monkey_company.monkey_health.domain.auth.repository.AuthRepository;
import com.monkey_company.monkey_health.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> member = authRepository.findByEmail(email);
        return member.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException(email));
    }

}
