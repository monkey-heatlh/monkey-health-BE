package com.monkey_company.monkey_health.domain.routine.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Builder
@Table(name = "routine")
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private RoutineContent mondayContent;

    private RoutineContent tuesdayContent;

    private RoutineContent wednesdayContent;

    private RoutineContent thursdayContent;

    private RoutineContent fridayContent;



}
