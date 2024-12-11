package com.monkey_company.monkey_health.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.UUID;

@Getter
@Builder
@ToString
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Setter
public class Member {

    @Id
    @Column(name = "member_id")
    @JsonProperty("user_id")
    @GeneratedValue
    private UUID id;

    @NotBlank
    @Pattern(regexp="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+[.][a-zA-Z]{2,3}$", message="이메일 주소 양식을 확인해주세요")
    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @NotBlank
    private String password;

    private String profilePath;

    public void updateProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

}
