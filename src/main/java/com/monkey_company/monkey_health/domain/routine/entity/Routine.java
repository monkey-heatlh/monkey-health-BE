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
@Setter
public class Routine {

    @Id
    private String email;
    @Enumerated(EnumType.STRING)
    private RoutineContent mondayContent;
    @Enumerated(EnumType.STRING)
    private RoutineContent tuesdayContent;
    @Enumerated(EnumType.STRING)
    private RoutineContent wednesdayContent;
    @Enumerated(EnumType.STRING)
    private RoutineContent thursdayContent;
    @Enumerated(EnumType.STRING)
    private RoutineContent fridayContent;



}
