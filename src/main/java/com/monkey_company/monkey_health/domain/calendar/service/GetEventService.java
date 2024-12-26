package com.monkey_company.monkey_health.domain.calendar.service;

import com.monkey_company.monkey_health.domain.calendar.entity.Calendar;

import java.time.LocalDate;
import java.util.List;

public interface GetEventService {
    List<Calendar> getEvent(LocalDate date, String token);
}
