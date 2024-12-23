package com.monkey_company.monkey_health.domain.calendar.service;

import com.monkey_company.monkey_health.domain.calendar.entity.Calendar;
import com.monkey_company.monkey_health.domain.calendar.repository.CalendarRepository;
import com.monkey_company.monkey_health.domain.member.repository.MemberRepository;
import com.monkey_company.monkey_health.global.security.jwt.TokenParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;
    private final MemberRepository memberRepository;
    private final TokenParser tokenParser;

//    public Calendar createEvent(Calendar calendar) {
//        return calendarRepository.save(calendar);
//    }
//
//    public List<Calendar> getEventsByDate(LocalDate date, String token) {
//        String email = tokenParser.getEmailFromToken(token);
//        return calendarRepository.findByDateAndEmail(date, email);
//    }
//
//    public void deleteEvent(Long eventId) {
//        calendarRepository.deleteById(eventId);
//    }
}
