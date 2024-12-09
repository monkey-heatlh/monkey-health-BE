package com.monkey_company.monkey_health.controller;

import com.monkey_company.monkey_health.domain.Routine;
import com.monkey_company.monkey_health.service.RoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/routine")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;

    @GetMapping("/{memberId}/date/{date}")
    public Routine getRoutineByMemberAndDate(@PathVariable UUID memberId, @PathVariable String date) {
        return routineService.getRoutineByMemberAndDate(memberId, date);
    }
}
