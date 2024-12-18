package com.monkey_company.monkey_health.domain.routine.dto.request;

import com.monkey_company.monkey_health.domain.routine.entity.RoutineContent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
public class RoutineRequest {

    @NotBlank private String email;
    @NotNull private RoutineContent mondayContent;
    @NotNull private RoutineContent tuesdayContent;
    @NotNull private RoutineContent wednesdayContent;
    @NotNull private RoutineContent thursdayContent;
    @NotNull private RoutineContent fridayContent;


}
