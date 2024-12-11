package com.monkey_company.monkey_health.controller.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class MemberRegisterResponse {

    private final String response;

    public MemberRegisterResponse(String response) {
        this.response = response;
    }

}
