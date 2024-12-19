package com.monkey_company.monkey_health.domain.routine.dto.request;

import com.monkey_company.monkey_health.domain.routine.entity.RoutineContent;
import lombok.*;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoutineRequest {

    private RoutineContent mondayContent;
    private RoutineContent tuesdayContent;
    private RoutineContent wednesdayContent;
    private RoutineContent thursdayContent;
    private RoutineContent fridayContent;


}
