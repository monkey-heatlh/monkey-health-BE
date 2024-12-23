package com.monkey_company.monkey_health.domain.calendar.repository;

import com.monkey_company.monkey_health.domain.calendar.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RestController
@RequestMapping("/calendar")
public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    Optional<Calendar> findByDateAndContent(LocalDate date, String content);

    List<Calendar> findByDateAndEmail(LocalDate date, String email);
}
