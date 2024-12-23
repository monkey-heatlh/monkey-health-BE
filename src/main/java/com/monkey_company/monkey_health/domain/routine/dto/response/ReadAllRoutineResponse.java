package com.monkey_company.monkey_health.domain.routine.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReadAllRoutineResponse {

    private String mondayContent;
    private String tuesdayContent;
    private String wednesdayContent;
    private String thursdayContent;
    private String fridayContent;
}
