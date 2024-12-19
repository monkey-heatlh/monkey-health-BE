package com.monkey_company.monkey_health.domain.routine.repository;

import com.monkey_company.monkey_health.domain.routine.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, String> {

    Optional<Routine> findByEmail(String email);
}
