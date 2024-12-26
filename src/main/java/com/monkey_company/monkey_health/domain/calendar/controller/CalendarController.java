package com.monkey_company.monkey_health.domain.calendar.controller;

import com.monkey_company.monkey_health.domain.calendar.dto.request.CalendarRequest;
import com.monkey_company.monkey_health.domain.calendar.dto.request.DeleteEventRequest;
import com.monkey_company.monkey_health.domain.calendar.dto.response.CalendarResponse;
import com.monkey_company.monkey_health.domain.calendar.entity.Calendar;
import com.monkey_company.monkey_health.domain.calendar.service.impl.DeleteEventServiceImpl;
import com.monkey_company.monkey_health.domain.calendar.service.impl.GetEventServiceImpl;
import com.monkey_company.monkey_health.domain.calendar.service.impl.SaveEventServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final DeleteEventServiceImpl deleteEventService;
    private final GetEventServiceImpl getEventService;
    private final SaveEventServiceImpl saveEventService;

    @GetMapping("/{date}")
    public List<Calendar> read(@PathVariable LocalDate date, @RequestHeader("Authorization") String token) {
        return getEventService.getEvent(date, token);
    }

    @PostMapping("/save/{date}")
    public CalendarResponse save(@PathVariable String date, @RequestBody CalendarRequest request, @RequestHeader("Authorization") String token) {
        LocalDate localDate = LocalDate.parse(date);
        return saveEventService.save(localDate, request.getContent(), token);
    }

    @DeleteMapping("/delete/{date}/{id}")
    public CalendarResponse delete(@PathVariable String date, @PathVariable Long id, @RequestHeader("Authorization") String token) {
        LocalDate localDate = LocalDate.parse(date);
        return deleteEventService.delete(localDate, id, token);
    }

}
