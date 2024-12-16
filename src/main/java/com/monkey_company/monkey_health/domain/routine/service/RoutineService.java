package com.monkey_company.monkey_health.domain.routine.service;

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

    public Routine getRoutineByMemberAndDate(String email, String date) {
        List<Routine> routines = routineRepository.findByEmailAndDate(email, LocalDate.parse(date));

        if (routines.isEmpty()) {
            throw new IllegalArgumentException("해당 날짜의 루틴을 찾을 수 없습니다.");
        }

        return routines.get(0);
    }


}
