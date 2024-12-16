package com.monkey_company.monkey_health.domain.routine.controller;

import com.monkey_company.monkey_health.domain.routine.service.RoutineService;
import com.monkey_company.monkey_health.domain.routine.entity.Routine;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/routine")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;

    @GetMapping("/{email}/date/{date}")
    public Routine getRoutineByMemberAndDate(@PathVariable String email, @PathVariable String date) {
        return routineService.getRoutineByMemberAndDate(email, date);
    }
}
