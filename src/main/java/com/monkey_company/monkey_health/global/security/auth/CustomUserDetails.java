package com.monkey_company.monkey_health.global.security.auth;


import com.monkey_company.monkey_health.domain.member.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

@Getter
@Setter
public class CustomUserDetails extends User {
    private Member member;

    public CustomUserDetails(Member member) {
        super(member.getEmail(), member.getPassword(), AuthorityUtils.NO_AUTHORITIES);
        this.member = member;
    }
}
