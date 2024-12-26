package com.monkey_company.monkey_health.domain.calendar.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteEventRequest {
    private LocalDate date;
    private Long id;
}
