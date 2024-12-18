package com.monkey_company.monkey_health.domain.auth.repository;

import com.monkey_company.monkey_health.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Member, String> {
    Optional<Member> findByEmail(String email);
}