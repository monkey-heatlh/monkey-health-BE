package com.monkey_company.monkey_health.domain.routine.controller;

import com.monkey_company.monkey_health.domain.routine.dto.request.SaveRoutineRequest;
import com.monkey_company.monkey_health.domain.routine.dto.response.ReadAllRoutineResponse;
import com.monkey_company.monkey_health.domain.routine.dto.response.SaveRoutineResponse;
import com.monkey_company.monkey_health.domain.routine.service.impl.ReadAllRoutineServiceImpl;
import com.monkey_company.monkey_health.domain.routine.service.impl.SaveRoutineServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/routine")
@RequiredArgsConstructor
public class RoutineController {

    private final ReadAllRoutineServiceImpl readAllRoutineService;
    private final SaveRoutineServiceImpl saveRoutineService;

    @GetMapping("/read")
    public ReadAllRoutineResponse readAllRoutine(@RequestHeader("Authorization") String token) {
        return readAllRoutineService.readAllRoutine(token);
    }

    @PostMapping("/save")
    public SaveRoutineResponse saveRoutine(@RequestBody SaveRoutineRequest request, @RequestHeader("Authorization") String token) {
        return saveRoutineService.saveRoutine(request, token);
    }
}
