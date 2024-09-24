package com.example.travelink.email;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EmailService implements EmailSender {

    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;



    @Override
    @Async
    public void sendEmail(String to, String email) {
        try {

            //Khai báo lớp hỗ trợ
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");

            //Thực hiện set bố cục của mail
            //Set true để có thể sử dụng định dạng HTML
            helper.setText(email, true); 
            helper.setTo(to);
            helper.setSubject("Thank you for start with Travelink");
            helper.setFrom("hungptfpt2004@gmail.com");
            javaMailSender.send(mimeMessage);
        } catch(MessagingException e) {

            ((org.slf4j.Logger) LOGGER).error("Failed to send email ", e);
            throw new IllegalStateException("Failed to send mail");

        }
    }
}