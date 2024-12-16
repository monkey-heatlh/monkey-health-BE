package com.monkey_company.monkey_health.domain.routine.dto.response;

import lombok.Builder;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Builder
public class RoutineResponse {
    private String content;
    private LocalDate date;
}
