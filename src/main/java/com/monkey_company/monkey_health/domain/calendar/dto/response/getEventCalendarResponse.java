package com.monkey_company.monkey_health.domain.calendar.dto.response;

import com.monkey_company.monkey_health.domain.calendar.entity.Calendar;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class getEventCalendarResponse {
    private List<Calendar> content;
}
