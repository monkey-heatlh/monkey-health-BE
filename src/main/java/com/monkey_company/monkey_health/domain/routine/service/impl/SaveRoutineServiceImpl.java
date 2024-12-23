package com.monkey_company.monkey_health.domain.routine.service.impl;

import com.monkey_company.monkey_health.domain.routine.dto.request.SaveRoutineRequest;
import com.monkey_company.monkey_health.domain.routine.dto.response.SaveRoutineResponse;
import com.monkey_company.monkey_health.domain.routine.entity.Routine;
import com.monkey_company.monkey_health.domain.routine.repository.RoutineRepository;
import com.monkey_company.monkey_health.domain.routine.service.SaveRoutineService;
import com.monkey_company.monkey_health.global.security.jwt.TokenParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SaveRoutineServiceImpl implements SaveRoutineService {

    private final RoutineRepository routineRepository;
    private final TokenParser tokenParser;

    @Override
    public SaveRoutineResponse saveRoutine(SaveRoutineRequest request, String token) {
        String email = tokenParser.getEmailFromToken(token);
        Optional<Routine> optionalRoutine = routineRepository.findByEmail(email);
        checkRoutine(optionalRoutine, request, email);
        return new SaveRoutineResponse("루틴이 저장되었습니다.");
    }

    private void checkRoutine(Optional<Routine> routine, SaveRoutineRequest request, String email) {
        if (routine.isPresent()) {
            Routine existingRoutine = routine.get();

            existingRoutine = existingRoutine.builder()
                    .email(email)
                    .mondayContent(request.getMondayContent())
                    .tuesdayContent(request.getTuesdayContent())
                    .wednesdayContent(request.getWednesdayContent())
                    .thursdayContent(request.getThursdayContent())
                    .fridayContent(request.getFridayContent())
                    .build();

            routineRepository.save(existingRoutine);
        } else {
            Routine newRoutine = Routine.builder()
                    .email(email)
                    .mondayContent(request.getMondayContent())
                    .tuesdayContent(request.getTuesdayContent())
                    .wednesdayContent(request.getWednesdayContent())
                    .thursdayContent(request.getThursdayContent())
                    .fridayContent(request.getFridayContent())
                    .build();

            routineRepository.save(newRoutine);
        }
    }
}
