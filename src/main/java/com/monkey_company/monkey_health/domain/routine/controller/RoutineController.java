package com.monkey_company.monkey_health.domain.routine.controller;

import com.monkey_company.monkey_health.domain.routine.dto.request.ReadAllRoutineRequest;
import com.monkey_company.monkey_health.domain.routine.dto.request.RoutineRequest;
import com.monkey_company.monkey_health.domain.routine.dto.response.ReadAllRoutineResponse;
import com.monkey_company.monkey_health.domain.routine.dto.response.SaveRoutineResponse;
import com.monkey_company.monkey_health.domain.routine.service.impl.ReadAllRoutineServiceImpl;
import com.monkey_company.monkey_health.domain.routine.service.impl.SaveRoutineServiceImpl;
import com.monkey_company.monkey_health.global.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/routine")
@RequiredArgsConstructor
public class RoutineController {

    private final ReadAllRoutineServiceImpl readAllRoutineService;
    private final SaveRoutineServiceImpl saveRoutineService;
    private final JwtUtil jwtUtil;

    @GetMapping("/read-all")
    public ReadAllRoutineResponse readAllRoutine(@RequestBody ReadAllRoutineRequest request) {
        return readAllRoutineService.readAllRoutine(request);
    }

    @PostMapping("/save")
    public SaveRoutineResponse saveRoutine(@RequestBody RoutineRequest request, @RequestHeader("Authorization") String token) {
        return saveRoutineService.saveRoutine(request, token);
    }
}
