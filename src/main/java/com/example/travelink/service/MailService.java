package com.example.travelink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String fromMail;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    // This method sends an email using an HTML template
    public void sendHtmlMail(String to, String subject, String verificationUrl) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        // Set email details
        helper.setFrom(fromMail);
        helper.setTo(to);
        helper.setSubject(subject);

        // Create the email body using Thymeleaf template
        Context context = new Context();
        context.setVariable("verificationUrl", verificationUrl); // Pass the verification link to the template
        String htmlContent = templateEngine.process("Verification_Email", context); // 'verification-email' is the
                                                                                    // Thymeleaf template name

        // Set the content as HTML
        helper.setText(htmlContent, true);

        // Send the email
        javaMailSender.send(mimeMessage);
    }

    public void sendSetPassWordEmail(String to, String subject, String verificationUrl) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        // Set email details
        helper.setFrom(fromMail);
        helper.setTo(to);
        helper.setSubject(subject);

        // Create the email body using Thymeleaf template
        Context context = new Context();
        context.setVariable("forgotPasswordUrl", verificationUrl); // Pass the verification link to the template
        String htmlContent = templateEngine.process("Forgot_Password", context); // 'verification-email' is the
                                                                                 // Thymeleaf template name

        // Set the content as HTML
        helper.setText(htmlContent, true);

        // Send the email
        javaMailSender.send(mimeMessage);
    }

}