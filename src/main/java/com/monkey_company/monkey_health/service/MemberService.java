package com.monkey_company.monkey_health.service;

import com.monkey_company.monkey_health.domain.Member;
import com.monkey_company.monkey_health.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member registerMember(String email, String password) {
        Member member = Member.builder()
                .email(email)
                .password(password)
                .build();

        return memberRepository.save(member);
    }

    @Transactional
    public void updateProfilePath(UUID memberId, String profilePath) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 회원을 찾을 수 없습니다: " + memberId));
        member.setProfilePath(profilePath);
        memberRepository.save(member);
    }

    public Member login(String email, String password) {
        Member member = memberRepository.findMemberByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일로 가입된 회원이 없습니다."));
        if (!member.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return member;
    }

    public Member getMemberById(UUID memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 회원을 찾을 수 없습니다: " + memberId));
    }
}
