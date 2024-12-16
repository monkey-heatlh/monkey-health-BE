package com.monkey_company.monkey_health.domain.calendar.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class CalendarRequest {

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull private LocalDate date;
    @NotBlank private String content;
}
