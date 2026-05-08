package com.elshoura.lokit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;


    @Override
    public void sendPasswordResetCode(String to, String code) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject("Lokit Password Reset Code");
        message.setText(
                "Hello,\n\n" +
                        "Your Lokit password reset verification code is: " + code + "\n\n" +
                        "This code will expire in 10 minutes.\n\n" +
                        "If you did not request a password reset, please ignore this email.\n\n" +
                        "Lokit Team"
        );

        mailSender.send(message);
    }

}
