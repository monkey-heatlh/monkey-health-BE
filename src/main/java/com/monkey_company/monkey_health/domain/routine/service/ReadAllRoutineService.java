package com.monkey_company.monkey_health.domain.routine.service;

import com.monkey_company.monkey_health.domain.routine.dto.request.ReadAllRoutineRequest;
import com.monkey_company.monkey_health.domain.routine.dto.response.ReadAllRoutineResponse;

public interface ReadAllRoutineService {
    ReadAllRoutineResponse readAllRoutine(String token);
}
