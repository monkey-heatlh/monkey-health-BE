package com.monkey_company.monkey_health.domain.member.controller;

import com.monkey_company.monkey_health.domain.member.dto.request.MemberProfilePathRequest;
import com.monkey_company.monkey_health.domain.member.dto.response.MemberResponse;
import com.monkey_company.monkey_health.domain.member.entity.Member;
import com.monkey_company.monkey_health.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PutMapping("/{memberId}/profile-path")
    public MemberResponse updateProfilePath(@PathVariable String email, @RequestBody MemberProfilePathRequest request) {
        memberService.updateProfilePath(email, request.getProfilePath());
        return new MemberResponse("프로필 경로가 업데이트되었습니다", null);
    }

    @GetMapping("/{memberId}")
    public MemberResponse getMemberInfo(@PathVariable String email) {
        Member member = memberService.getMemberById(email);
        return new MemberResponse(member.getEmail(), member.getProfilePath());
    }

}
