package com.monkey_company.monkey_health.domain.auth.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Builder
@AllArgsConstructor
@RedisHash(value = "refreshToken")
public class RefreshToken {

    @Id  // 반드시 @Id 어노테이션을 추가해야 합니다.
    @Indexed
    private String token;  // token을 고유 식별자로 사용할 수 있습니다.

    @Indexed
    private Long userId;  // userId는 @Indexed로 추가하여 검색에 사용

    @Indexed
    private int expirationTime;  // 만료 시간
}
