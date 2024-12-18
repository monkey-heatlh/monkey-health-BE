package com.monkey_company.monkey_health.domain.auth.service.impl;

import com.monkey_company.monkey_health.domain.auth.dto.response.EmailverificationResponse;
import com.monkey_company.monkey_health.domain.auth.service.SendMailService;
import com.monkey_company.monkey_health.domain.member.repository.MemberRepository;
import com.monkey_company.monkey_health.global.error.GlobalException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class SendMailServiceImpl implements SendMailService {

    private final MemberRepository memberRepository;
    private final JavaMailSender javaMailSender;
    private static final String senderEmail = "monkeyCompany53@gmail.com";
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public EmailverificationResponse sendMail(String email) {
        int verificationCode = generateVerificationCode();
        checkSignUpEmail(email);
        redisTemplate.opsForValue().set(email, String.valueOf(verificationCode), 5, TimeUnit.MINUTES);
        MimeMessage message = createMail(email, verificationCode);
        javaMailSender.send(message);
        return new EmailverificationResponse("인증 번호가 발송되었습니다.");
    }

    private int generateVerificationCode() {
        return (int) (Math.random() * (900000)) + 100000;
    }

    private void checkSignUpEmail(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new GlobalException("이미 가입된 이메일입니다.", HttpStatus.CONFLICT);
        }
    }

    private MimeMessage createMail(String email, int verificationCode) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, email);
            message.setSubject("이메일 인증");
            String body = "<h3>" + "요청하신 인증 번호입니다." + "</h3>" +
                    "<h1>" + verificationCode + "</h1>" +
                    "<h3>" + "감사합니다." + "</h3>";
            message.setText(body, "UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return message;

    }
}
