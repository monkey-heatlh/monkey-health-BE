package com.monkey_company.monkey_health.domain.routine.service;

import com.monkey_company.monkey_health.domain.routine.dto.request.RoutineRequest;
import com.monkey_company.monkey_health.domain.routine.entity.Routine;
import com.monkey_company.monkey_health.domain.routine.repository.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RoutineService {

    private final RoutineRepository routineRepository;

    public Routine saveRoutine(RoutineRequest request) {
        Routine routine = Routine.builder()
                .email(request.getEmail())
                .build();
        return null;
    }

}
