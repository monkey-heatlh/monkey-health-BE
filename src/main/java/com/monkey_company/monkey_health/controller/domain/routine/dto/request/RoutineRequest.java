package com.monkey_company.monkey_health.controller.domain.routine.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.monkey_company.monkey_health.controller.domain.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@ToString
@Getter
public class RoutineRequest {


    @NotBlank private String content;
    @NotBlank private Member member;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull private LocalDate date;

    @Builder
    public RoutineRequest(String content, Member member, LocalDate date) {
        this.content = content;
        this.member = member;
        this.date = date;
    }

}
