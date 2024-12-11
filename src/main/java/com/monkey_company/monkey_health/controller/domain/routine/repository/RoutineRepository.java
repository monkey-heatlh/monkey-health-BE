package com.monkey_company.monkey_health.controller.domain.routine.repository;

import com.monkey_company.monkey_health.controller.domain.routine.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Integer> {
    List<Routine> findByMemberIdAndDate(UUID memberId, LocalDate parse);
}
