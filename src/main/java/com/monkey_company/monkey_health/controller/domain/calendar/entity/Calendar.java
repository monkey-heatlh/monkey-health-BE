package com.monkey_company.monkey_health.controller.domain.calendar.entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 생성
    @Column(name = "calendar_id") // 기본키 컬럼
    private Long id;

    @Column(name = "calendar_date", nullable = false)
    private LocalDate date; // 날짜는 중복 가능

    @Column(name = "calendar_content", nullable = false, length = 500) // 길이 제한 추가
    private String content; // 같은 날짜에 여러 콘텐츠 가능
}
