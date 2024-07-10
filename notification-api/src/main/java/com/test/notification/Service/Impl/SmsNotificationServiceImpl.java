package com.test.notification.Service.Impl;

import com.test.notification.Model.NotificationRequest;
import com.test.notification.Model.Template;
import com.test.notification.Service.NotificationSender;
import lombok.Setter;

@Setter
public class SmsNotificationServiceImpl implements NotificationSender {

    @Override
    public void send(NotificationRequest notificationRequest) {
        // get template from mongodb by notificationTemplate notification id provided
        Template template = new Template();

        // send sms
    }
}
