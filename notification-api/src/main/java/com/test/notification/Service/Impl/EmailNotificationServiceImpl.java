package com.test.notification.Service.Impl;

import com.test.notification.Model.NotificationRequest;
import com.test.notification.Model.Template;
import com.test.notification.Service.NotificationSender;
import lombok.Setter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Setter
public class EmailNotificationServiceImpl implements NotificationSender {

    private JavaMailSender mailSender;

    @Override
    public void send(NotificationRequest notificationRequest) {
        // replace new Template with get template from mongodb by notificationTemplate notification id provided
        Template template = new Template().replaceValues(notificationRequest.getEntries());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("support@person.com");
        message.setTo(notificationRequest.getRecipient());
        message.setSubject(template.getTitle());
        message.setText(template.getBody());
        mailSender.send(message);
    }
}