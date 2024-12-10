package com.monkey_company.monkey_health.service;

import com.monkey_company.monkey_health.domain.Routine;
import com.monkey_company.monkey_health.repository.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RoutineService {

    private final RoutineRepository routineRepository;

    public Routine getRoutineByMemberAndDate(UUID memberId, String date) {
        List<Routine> routines = routineRepository.findByMemberIdAndDate(memberId, LocalDate.parse(date));

        if (routines.isEmpty()) {
            throw new IllegalArgumentException("해당 날짜의 루틴을 찾을 수 없습니다.");
        }

        return routines.get(0);
    }


}
