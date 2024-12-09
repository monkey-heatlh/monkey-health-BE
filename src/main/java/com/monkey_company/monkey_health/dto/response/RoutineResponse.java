package com.monkey_company.monkey_health.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey_company.monkey_health.domain.Routine;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Builder
public class RoutineResponse {
    private String content;
    private LocalDate date;
}
