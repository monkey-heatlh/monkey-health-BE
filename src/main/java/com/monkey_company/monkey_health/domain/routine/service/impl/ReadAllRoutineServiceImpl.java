package com.monkey_company.monkey_health.domain.routine.service.impl;

import com.monkey_company.monkey_health.domain.routine.dto.response.ReadAllRoutineResponse;
import com.monkey_company.monkey_health.domain.routine.entity.Routine;
import com.monkey_company.monkey_health.domain.routine.repository.RoutineRepository;
import com.monkey_company.monkey_health.domain.routine.service.ReadAllRoutineService;
import com.monkey_company.monkey_health.global.error.GlobalException;
import com.monkey_company.monkey_health.global.security.jwt.TokenParser;
import com.monkey_company.monkey_health.global.translation.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadAllRoutineServiceImpl implements ReadAllRoutineService {

    private final RoutineRepository routineRepository;
    private final TokenParser tokenParser;
    private final TranslationService translationService;

    @Override
    public ReadAllRoutineResponse readAllRoutine(String token) {
        String email = tokenParser.getEmailFromToken(token);
        Routine routine = routineRepository.findByEmail(email)
                .orElseThrow(() -> new GlobalException("루틴을 찾을 수 없습니다", HttpStatus.NOT_FOUND));

        if (isRoutineEmpty(routine)) {
            throw new GlobalException("루틴을 찾을 수 없습니다", HttpStatus.NOT_FOUND);
        }

        return createTranslatedResponse(routine);
    }

    private boolean isRoutineEmpty(Routine routine) {
        return routine.getMondayContent() == null &&
                routine.getTuesdayContent() == null &&
                routine.getWednesdayContent() == null &&
                routine.getThursdayContent() == null &&
                routine.getFridayContent() == null;
    }

    private ReadAllRoutineResponse createTranslatedResponse(Routine routine) {
        return new ReadAllRoutineResponse(
                translationService.translateToKorean(routine.getMondayContent()),
                translationService.translateToKorean(routine.getTuesdayContent()),
                translationService.translateToKorean(routine.getWednesdayContent()),
                translationService.translateToKorean(routine.getThursdayContent()),
                translationService.translateToKorean(routine.getFridayContent())
        );
    }
}
