package com.monkey_company.monkey_health.domain.calendar.service.impl;

import com.monkey_company.monkey_health.domain.calendar.dto.response.CalendarResponse;
import com.monkey_company.monkey_health.domain.calendar.entity.Calendar;
import com.monkey_company.monkey_health.domain.calendar.repository.CalendarRepository;
import com.monkey_company.monkey_health.domain.calendar.service.SaveEventService;
import com.monkey_company.monkey_health.global.security.jwt.TokenParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SaveEventServiceImpl implements SaveEventService {

    private final CalendarRepository calendarRepository;
    private final TokenParser tokenParser;

    @Override
    public CalendarResponse save(LocalDate date, String content, String token) {
        String email = tokenParser.getEmailFromToken(token);
        Calendar newCalendar = createEvent(date, content, email);
        calendarRepository.save(newCalendar);
        return new CalendarResponse("메모가 저장되었습니다");
    }

    private Calendar createEvent(LocalDate date, String content, String email) {
        Calendar calendar = Calendar.builder()
                .email(email)
                .content(content)
                .date(date)
                .build();

        return calendar;
    }
}
