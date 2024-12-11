package com.monkey_company.monkey_health.controller.domain.routine.entity;

import com.monkey_company.monkey_health.controller.domain.member.entity.Member;
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
    private Integer routineId;

    @OneToOne
    @JoinColumn(name = "member_id", nullable = false, updatable = false)
    private Member member;

    private String content;

    private LocalDate date;
}
