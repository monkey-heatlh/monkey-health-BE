package com.monkey_company.monkey_health.domain.auth.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private static final String senderEmail = "monkeyCompany53@gmail.com";
    private final RedisTemplate<String, String> redisTemplate;

    // 랜덤으로 인증 코드 생성
    private int generateVerificationCode() {
        return (int) (Math.random() * (900000)) + 100000; // 6자리 숫자 생성
    }

    // 이메일 메시지 생성
    public MimeMessage createMail(String email, int verificationCode) {
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

    // 이메일 전송 및 Redis에 인증 코드 저장
    public int sendMail(String email) {
        int verificationCode = generateVerificationCode();

        // Redis에 인증 코드 저장 (5분 유효 기간 설정)
        redisTemplate.opsForValue().set(email, String.valueOf(verificationCode), 5, TimeUnit.MINUTES);
        // 이메일 메시지 생성
        MimeMessage message = createMail(email, verificationCode);
        javaMailSender.send(message);

        return verificationCode; // 생성한 인증 코드를 반환
    }
}
