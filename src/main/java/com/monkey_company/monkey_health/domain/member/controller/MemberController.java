package com.monkey_company.monkey_health.domain.member.controller;

import com.monkey_company.monkey_health.domain.member.dto.request.MemberProfilePathRequest;
import com.monkey_company.monkey_health.domain.member.dto.request.MemberRequest;
import com.monkey_company.monkey_health.domain.member.dto.response.MemberRegisterResponse;
import com.monkey_company.monkey_health.domain.member.dto.response.MemberResponse;
import com.monkey_company.monkey_health.domain.member.entity.Member;
import com.monkey_company.monkey_health.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 프로필 경로 업데이트
    @PutMapping("/{memberId}/profile-path")
    public MemberResponse updateProfilePath(@PathVariable UUID memberId, @RequestBody MemberProfilePathRequest request) {
        memberService.updateProfilePath(memberId, request.getProfilePath());
        return new MemberResponse("프로필 경로가 업데이트되었습니다", null);
    }

    // 회원 정보 조회
    @GetMapping("/{memberId}")
    public MemberResponse getMemberInfo(@PathVariable UUID memberId) {
        Member member = memberService.getMemberById(memberId);
        return new MemberResponse(member.getEmail(), member.getProfilePath());
    }

}
