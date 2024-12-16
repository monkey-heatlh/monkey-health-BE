package com.monkey_company.monkey_health.domain.routine.entity;

import com.monkey_company.monkey_health.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Builder
public class Routine {

    @Id
    @GeneratedValue
    @Column(name = "routine_id")
    private Long routineId;

    @JoinColumn(name = "email", nullable = false, updatable = false)
    private String email;

    private String content;

    private LocalDate date;
}
