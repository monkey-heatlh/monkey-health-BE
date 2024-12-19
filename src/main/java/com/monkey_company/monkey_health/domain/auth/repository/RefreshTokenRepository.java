package com.monkey_company.monkey_health.domain.auth.repository;

import com.monkey_company.monkey_health.domain.auth.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByEmail(String email);
}
