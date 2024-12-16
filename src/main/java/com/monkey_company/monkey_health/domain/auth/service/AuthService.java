package com.monkey_company.monkey_health.domain.auth.service;

import com.monkey_company.monkey_health.domain.member.entity.Member;
import com.monkey_company.monkey_health.domain.auth.dto.request.AuthRequest;
import com.monkey_company.monkey_health.domain.auth.dto.response.LoginResponse;
import com.monkey_company.monkey_health.domain.auth.repository.AuthRepository;
import com.monkey_company.monkey_health.global.error.GlobalException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public Member register(AuthRequest request) {
        // 이메일 중복 확인
        if (authRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new GlobalException("이미 가입된 이메일입니다", HttpStatus.CONFLICT);
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 회원 생성 및 저장
        Member member = Member.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .build();

        return authRepository.save(member);
    }

    public LoginResponse login(AuthRequest request, HttpSession session) {
        // 이메일로 사용자를 찾고, 없으면 ExpectedException을 던짐
        Member member = authRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new GlobalException("사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        // 비밀번호가 일치하는지 확인하고, 일치하지 않으면 ExpectedException을 던짐
        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new GlobalException("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
        }

        // 세션에 사용자 정보 저장
        session.setAttribute("user", member);

        // 로그인 성공 응답
        return new LoginResponse("로그인 성공");
    }






}
