package com.monkey_company.monkey_health.domain.calendar.service.impl;

import com.monkey_company.monkey_health.domain.calendar.entity.Calendar;
import com.monkey_company.monkey_health.domain.calendar.repository.CalendarRepository;
import com.monkey_company.monkey_health.domain.calendar.service.GetEventService;
import com.monkey_company.monkey_health.global.security.jwt.TokenParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetEventServiceImpl implements GetEventService {

    private final CalendarRepository calendarRepository;
    private final TokenParser tokenParser;

    @Override
    public List<Calendar> getEvent(LocalDate date, String token) {
        String email = tokenParser.getEmailFromToken(token);
        List<Calendar> event = calendarRepository.findIdAndContentByDateAndEmail(date, email);
        return event;
    }
}
