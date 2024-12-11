package com.monkey_company.monkey_health.domain.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberProfilePathRequest {
    @NotBlank private String profilePath;

    public MemberProfilePathRequest(String profilePath) {
        this.profilePath = profilePath;
    }
}
