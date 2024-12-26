package com.monkey_company.monkey_health.domain.calendar.service;

import com.monkey_company.monkey_health.domain.calendar.dto.response.CalendarResponse;

import java.time.LocalDate;

public interface DeleteEventService {
    CalendarResponse delete(LocalDate date, Long id, String token);
}
