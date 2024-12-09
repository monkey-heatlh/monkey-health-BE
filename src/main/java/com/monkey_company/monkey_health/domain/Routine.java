package com.monkey_company.monkey_health.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

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
