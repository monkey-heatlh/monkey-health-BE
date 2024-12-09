package com.monkey_company.monkey_health.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class MemberResponse {
    private String email;
    private String profilePath;

    public MemberResponse(String email, String profilePath) {
        this.email = email;
        this.profilePath = profilePath;
    }
}
