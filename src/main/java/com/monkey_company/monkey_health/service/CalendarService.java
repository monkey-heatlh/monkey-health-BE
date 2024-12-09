package com.monkey_company.monkey_health.service;

import com.monkey_company.monkey_health.domain.Calendar;
import com.monkey_company.monkey_health.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CalendarService {

    private final CalendarRepository calendarRepository;

    public List<Calendar> getEventsByDate(LocalDate date) {
        return calendarRepository.findByDate(date);
    }

    @Transactional
    public Calendar saveEvent(LocalDate date, String content) {
        Calendar calendar = Calendar.builder()
                .content(content)
                .date(date)
                .build();

        return calendar;
    }

    @Transactional
    public void deleteEvent(LocalDate date, String content) {
        Calendar calendar = calendarRepository.findByDateAndContent(date, content)
                .orElseThrow(() -> new IllegalArgumentException("Event not found for the given date and content."));
        calendarRepository.delete(calendar);
    }

}
