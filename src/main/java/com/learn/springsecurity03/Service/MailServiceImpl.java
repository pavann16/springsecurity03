package com.learn.springsecurity03.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class MailServiceImpl{

    @Autowired
    private JavaMailSender javaMailSender;

    //@EventListener(ApplicationReadyEvent.class)
    public String sendMail(String to, String body, String subject){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setTo(to);
        message.setText(body);
        message.setFrom("saipavan.samala@gmail.com");

        try {
            javaMailSender.send(message);
        }
        catch(Exception e)
        {
            return e.getMessage();
        }

        return "Mail Sent!!";

    }

}
