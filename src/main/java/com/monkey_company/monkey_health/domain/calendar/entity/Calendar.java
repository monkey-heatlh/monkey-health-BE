package com.monkey_company.monkey_health.domain.calendar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "calendar_events") // 테이블 이름 명시
public class Calendar {

    @Id
    private String email;

    private LocalDate date; // 날짜는 중복 가능

    private String content; // 같은 날짜에 여러 콘텐츠 가능
}
