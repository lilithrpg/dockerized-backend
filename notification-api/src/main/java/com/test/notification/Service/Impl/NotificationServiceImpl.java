package com.test.notification.Service.Impl;

import com.test.notification.Model.NotificationRequest;
import com.test.notification.Model.Template;
import com.test.notification.Service.NotificationSender;
import com.test.notification.Service.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Override
    public void execute(NotificationRequest notificationRequest) {
        // replace new Template with get template from mongodb by notificationTemplate notification id provided
        Template template = new Template().replaceValues(notificationRequest.getEntries());

        NotificationSender notificationSender = switch (notificationRequest.getType()) {
            case "sms" -> new SmsNotificationServiceImpl();
            case "app" -> new AppNotificationServiceImpl();
            default -> new EmailNotificationServiceImpl();
        };

        notificationSender.send(notificationRequest);
    }
}