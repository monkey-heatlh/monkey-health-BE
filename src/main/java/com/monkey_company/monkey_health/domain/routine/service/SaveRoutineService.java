package com.monkey_company.monkey_health.domain.routine.service;

import com.monkey_company.monkey_health.domain.routine.dto.request.RoutineRequest;
import com.monkey_company.monkey_health.domain.routine.dto.response.SaveRoutineResponse;

public interface SaveRoutineService {
    SaveRoutineResponse saveRoutine(RoutineRequest request, String token);
}
