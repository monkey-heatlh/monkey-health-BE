package com.monkey_company.monkey_health.domain.routine.dto.response;

import com.monkey_company.monkey_health.domain.routine.entity.RoutineContent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReadAllRoutineResponse {

    private RoutineContent mondayContent;
    private RoutineContent tuesdayContent;
    private RoutineContent wednesdayContent;
    private RoutineContent thursdayContent;
    private RoutineContent fridayContent;
}
