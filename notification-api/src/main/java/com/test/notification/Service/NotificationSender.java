package com.test.notification.Service;

import com.test.notification.Model.NotificationRequest;

public interface NotificationSender {
    void send(NotificationRequest notificationRequest);
}
