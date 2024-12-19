package com.monkey_company.monkey_health.domain.routine.service.impl;

import com.monkey_company.monkey_health.domain.routine.dto.request.RoutineRequest;
import com.monkey_company.monkey_health.domain.routine.dto.response.SaveRoutineResponse;
import com.monkey_company.monkey_health.domain.routine.entity.Routine;
import com.monkey_company.monkey_health.domain.routine.repository.RoutineRepository;
import com.monkey_company.monkey_health.domain.routine.service.SaveRoutineService;
import com.monkey_company.monkey_health.global.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaveRoutineServiceImpl implements SaveRoutineService {

    private final RoutineRepository routineRepository;
    private final JwtUtil jwtUtil;

    @Override
    public SaveRoutineResponse saveRoutine(RoutineRequest request, String token) {
        String email = jwtUtil.getEmailFromToken(token);
        Optional<Routine> optionalRoutine = routineRepository.findByEmail(email);
        checkRoutine(optionalRoutine, request, email);
        return new SaveRoutineResponse("루틴이 저장되었습니다.");
    }

    private void checkRoutine(Optional<Routine> routine, RoutineRequest request, String email) {
        if (routine.isPresent()) {
            Routine existingRoutine = routine.get();
            existingRoutine.setMondayContent(request.getMondayContent());
            existingRoutine.setTuesdayContent(request.getTuesdayContent());
            existingRoutine.setWednesdayContent(request.getWednesdayContent());
            existingRoutine.setThursdayContent(request.getThursdayContent());
            existingRoutine.setFridayContent(request.getFridayContent());

        } else {
            Routine newRoutine = createRoutine(request, email);
            routineRepository.save(newRoutine);
        }
    }

    private Routine createRoutine(RoutineRequest request, String email) {
        Routine routine = Routine.builder()
                .email(email)
                .mondayContent(request.getMondayContent())
                .tuesdayContent(request.getTuesdayContent())
                .wednesdayContent(request.getWednesdayContent())
                .thursdayContent(request.getThursdayContent())
                .fridayContent(request.getFridayContent())
                .build();

        return routine;
    }
}
