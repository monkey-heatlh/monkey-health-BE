package com.monkey_company.monkey_health.domain.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReissueResponse {

    private String newAccessToken;
    private String newRefreshToken;
}
