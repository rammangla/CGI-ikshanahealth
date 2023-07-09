package com.stackroute.userauthservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOtpMessage(String to,String subject,String message  ) throws MessagingException {

        MimeMessage msg =javaMailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(msg,true);
        /* Setting email parameter*/
        helper.setTo(to); //where mail will be send
        helper.setSubject(subject); //Subject of the mail
        helper.setText(message,true); //message of the mail
        javaMailSender.send(msg); //sending email

    }
}
