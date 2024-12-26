package com.monkey_company.monkey_health.domain.calendar.service;

import com.monkey_company.monkey_health.domain.calendar.dto.response.CalendarResponse;

import java.time.LocalDate;

public interface SaveEventService {
    CalendarResponse save(LocalDate date, String content, String token);
}
