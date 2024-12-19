package com.monkey_company.monkey_health.domain.routine.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReadAllRoutineRequest {

    @NotBlank private String email;
}
