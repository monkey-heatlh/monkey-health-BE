package com.monkey_company.monkey_health.repository;

import com.monkey_company.monkey_health.domain.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    List<Calendar> findByDate(LocalDate calendarDate);

    Optional<Calendar> findByDateAndContent(LocalDate date, String content);
}
