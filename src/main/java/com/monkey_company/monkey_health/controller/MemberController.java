package com.monkey_company.monkey_health.controller;

import com.monkey_company.monkey_health.dto.request.MemberProfilePathRequest;
import com.monkey_company.monkey_health.dto.request.MemberRequest;
import com.monkey_company.monkey_health.dto.response.MemberResponse;
import com.monkey_company.monkey_health.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.monkey_company.monkey_health.domain.Member;

import java.util.UUID;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원 가입
    @PostMapping("/register")
    public MemberResponse registerMember(@RequestBody MemberRequest request) {
        Member member = memberService.registerMember(request.getEmail(), request.getPassword());
        return new MemberResponse(member.getEmail(), member.getProfilePath());
    }

    // 프로필 경로 업데이트
    @PutMapping("/{memberId}/profile-path")
    public MemberResponse updateProfilePath(@PathVariable UUID memberId, @RequestBody MemberProfilePathRequest request) {
        memberService.updateProfilePath(memberId, request.getProfilePath());
        return new MemberResponse("프로필 경로가 업데이트되었습니다", null);
    }

    // 로그인 기능
    @PostMapping("/login")
    public MemberResponse login(@RequestBody MemberRequest request) {
        // 로그인 로직 (이메일과 비밀번호 검증)
        Member member = memberService.login(request.getEmail(), request.getPassword());
        return new MemberResponse(member.getEmail(), member.getProfilePath());
    }

    // 회원 정보 조회
    @GetMapping("/{memberId}")
    public MemberResponse getMemberInfo(@PathVariable UUID memberId) {
        Member member = memberService.getMemberById(memberId);
        return new MemberResponse(member.getEmail(), member.getProfilePath());
    }

}
