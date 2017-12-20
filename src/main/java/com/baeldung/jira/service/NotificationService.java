package com.baeldung.jira.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("notoficationService")
public class NotificationService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private Environment env;

    @Autowired
    private JavaMailSender mailSender;

    private SimpleMailMessage constructResetTokenEmail(String userEmail, String userName) {
        final String message = "Dear " + userName + " \n Complete your issues as soon as possible we have observed you have more issues";
        return constructEmail("Alert Mail for your JIRA Tasks", message + " \r\n", userName);
    }

    private SimpleMailMessage constructEmail(String subject, String body, String userEmail) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(env.getProperty("support.email"));// (userEmail);
        email.setFrom(env.getProperty("support.email"));// Jira User Admin
        return email;
    }

    public void notifyUser(String userEmail, String jiraUser) {
        LOGGER.info("sending notification to user :" + jiraUser);
        mailSender.send(constructResetTokenEmail(userEmail, jiraUser));
    }

}
