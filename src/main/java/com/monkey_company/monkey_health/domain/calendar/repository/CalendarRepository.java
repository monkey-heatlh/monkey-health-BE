package com.monkey_company.monkey_health.domain.calendar.repository;

import com.monkey_company.monkey_health.domain.calendar.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Repository
@RestController
@RequestMapping("/calendar")
public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    List<Calendar> findIdAndContentByDateAndEmail(LocalDate date, String email);

    Calendar findByDateAndIdAndAndEmail(LocalDate date, Long id, String email);
}
