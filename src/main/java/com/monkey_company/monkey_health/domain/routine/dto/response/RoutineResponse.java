package com.monkey_company.monkey_health.domain.routine.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class RoutineResponse {

    private String message;

    public RoutineResponse(String message) {
        this.message = message;
    }
}
