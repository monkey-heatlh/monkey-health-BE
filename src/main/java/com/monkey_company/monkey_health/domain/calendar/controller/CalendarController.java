package com.monkey_company.monkey_health.domain.calendar.controller;

import com.monkey_company.monkey_health.domain.calendar.entity.Calendar;
import com.monkey_company.monkey_health.domain.calendar.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

//    @PostMapping("/events")
//    public Calendar createEvent(@RequestBody Calendar calendar) {
//        return calendarService.createEvent(calendar);
//    }
//
//    @GetMapping("/read")
//    public List<Calendar> readCalendar(LocalDate date, @RequestHeader String token) {
//        return calendarService.getEventsByDate(date, token);
//    }


}
