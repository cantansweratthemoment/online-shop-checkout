package com.blps.firstlaboratory.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.*;
import java.util.Properties;

@Component
public class MailConfig {
    @Value("${mail.login}")
    String MY_EMAIL;
    @Value("${mail.password}")
    String MY_PASSWORD;

    Session session;

    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    @PostConstruct
    void init() throws MessagingException {
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.host", "smtp.yandex.ru");
        props.put("mail.smtp.user",MY_EMAIL);
        props.put("mail.smtp.password",MY_PASSWORD);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.auth", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.test-connection", "true");
        props.put("mail.debug", "true");

        this.session = Session.getDefaultInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(MY_EMAIL, MY_PASSWORD);
                    }
                });
    }

    public Session getSession() {
        return session;
    }
}