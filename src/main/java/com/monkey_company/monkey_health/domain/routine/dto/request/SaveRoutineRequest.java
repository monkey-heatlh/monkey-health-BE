package com.monkey_company.monkey_health.domain.routine.dto.request;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaveRoutineRequest {

    @JsonProperty("monday_content")
    private String mondayContent;

    @JsonProperty("tuesday_content")
    private String tuesdayContent;

    @JsonProperty("wednesday_content")
    private String wednesdayContent;

    @JsonProperty("thursday_content")
    private String thursdayContent;

    @JsonProperty("friday_content")
    private String fridayContent;
}
