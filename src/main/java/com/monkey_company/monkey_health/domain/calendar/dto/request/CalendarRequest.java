package com.monkey_company.monkey_health.domain.calendar.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CalendarRequest {
    @NotBlank private String content;
}
