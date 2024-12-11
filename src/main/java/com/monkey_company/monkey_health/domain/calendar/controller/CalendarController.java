package com.monkey_company.monkey_health.domain.calendar.controller;

import com.monkey_company.monkey_health.domain.calendar.dto.request.CalendarRequest;
import com.monkey_company.monkey_health.domain.calendar.dto.response.CalendarResponse;
import com.monkey_company.monkey_health.domain.calendar.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping
    public List<CalendarResponse> getEventsByDate(@RequestParam("date") LocalDate date) {
        return calendarService.getEventsByDate(date)
                .stream()
                .map(event -> CalendarResponse.builder()
                        .content(event.getContent())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping
    public CalendarResponse saveEvent(@RequestBody CalendarRequest calendarRequest) {
        var savedEvent = calendarService.saveEvent(calendarRequest.getDate(), calendarRequest.getContent());
        return CalendarResponse.builder()
                .content(savedEvent.getContent())
                .build();
    }

    @DeleteMapping
    public String deleteEvent(@RequestBody CalendarRequest calendarRequest) {
        calendarService.deleteEvent(calendarRequest.getDate(), calendarRequest.getContent());
        return "이벤트가 삭제되었습니다.";
    }
}
