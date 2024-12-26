package com.monkey_company.monkey_health.domain.routine.service.impl;

import com.monkey_company.monkey_health.domain.routine.dto.request.SaveRoutineRequest;
import com.monkey_company.monkey_health.domain.routine.dto.response.SaveRoutineResponse;
import com.monkey_company.monkey_health.domain.routine.entity.Routine;
import com.monkey_company.monkey_health.domain.routine.repository.RoutineRepository;
import com.monkey_company.monkey_health.domain.routine.service.SaveRoutineService;
import com.monkey_company.monkey_health.global.security.jwt.TokenParser;
import com.monkey_company.monkey_health.global.translation.TranslationService;
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
    private final TranslationService translationService;

    @Override
    public SaveRoutineResponse saveRoutine(SaveRoutineRequest request, String token) {
        String email = tokenParser.getEmailFromToken(token);
        Optional<Routine> optionalRoutine = routineRepository.findByEmail(email);
        SaveRoutineRequest translatedRequest = translateRequestToEnglish(request);
        checkRoutine(optionalRoutine, translatedRequest, email);
        return new SaveRoutineResponse("루틴이 저장되었습니다.");
    }

    private void checkRoutine(Optional<Routine> routine, SaveRoutineRequest request, String email) {
        Routine routineToSave = routine.map(existingRoutine ->
                        updateExistingRoutine(existingRoutine, request))
                .orElse(createNewRoutine(request, email));

        routineRepository.save(routineToSave);
    }

    private Routine updateExistingRoutine(Routine routine, SaveRoutineRequest request) {
        return Routine.builder()
                .email(routine.getEmail())
                .mondayContent(request.getMondayContent())
                .tuesdayContent(request.getTuesdayContent())
                .wednesdayContent(request.getWednesdayContent())
                .thursdayContent(request.getThursdayContent())
                .fridayContent(request.getFridayContent())
                .build();
    }


    private Routine createNewRoutine(SaveRoutineRequest request, String email) {
        return Routine.builder()
                .email(email)
                .mondayContent(request.getMondayContent())
                .tuesdayContent(request.getTuesdayContent())
                .wednesdayContent(request.getWednesdayContent())
                .thursdayContent(request.getThursdayContent())
                .fridayContent(request.getFridayContent())
                .build();
    }

    private SaveRoutineRequest translateRequestToEnglish(SaveRoutineRequest request) {
        return SaveRoutineRequest.builder()
                .mondayContent(translationService.translateToEnglish(request.getMondayContent()))
                .tuesdayContent(translationService.translateToEnglish(request.getTuesdayContent()))
                .wednesdayContent(translationService.translateToEnglish(request.getWednesdayContent()))
                .thursdayContent(translationService.translateToEnglish(request.getThursdayContent()))
                .fridayContent(translationService.translateToEnglish(request.getFridayContent()))
                .build();
    }
}
