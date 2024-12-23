package com.monkey_company.monkey_health.domain.routine.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReadAllRoutineResponse {

    @Column(name = "monday_content")
    private String mondayContent;
    @Column(name = "tuesday_content")
    private String tuesdayContent;
    @Column(name = "wednesday_content")
    private String wednesdayContent;
    @Column(name = "thursday_content")
    private String thursdayContent;
    @Column(name = "friday_content")
    private String fridayContent;
}
