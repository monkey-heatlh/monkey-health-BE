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

    //public
}
