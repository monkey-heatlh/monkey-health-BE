package com.monkey_company.monkey_health.domain.routine.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "email", nullable = false, unique = true)
    private String email;

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
