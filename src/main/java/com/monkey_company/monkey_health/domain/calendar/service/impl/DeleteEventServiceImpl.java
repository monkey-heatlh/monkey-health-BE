package com.monkey_company.monkey_health.domain.calendar.service.impl;

import com.monkey_company.monkey_health.domain.calendar.dto.response.CalendarResponse;
import com.monkey_company.monkey_health.domain.calendar.entity.Calendar;
import com.monkey_company.monkey_health.domain.calendar.repository.CalendarRepository;
import com.monkey_company.monkey_health.domain.calendar.service.DeleteEventService;
import com.monkey_company.monkey_health.global.security.jwt.TokenParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DeleteEventServiceImpl implements DeleteEventService {

    private final CalendarRepository calendarRepository;
    private final TokenParser tokenParser;

    @Override
    public CalendarResponse delete(LocalDate date, Long id, String token) {
        String email = tokenParser.getEmailFromToken(token);
        Calendar contents = calendarRepository.findByDateAndIdAndAndEmail(date, id, email);
        calendarRepository.delete(contents);
        return new CalendarResponse("메모가 삭제되었습니다");
    }

}
