package com.test.notification.Service;

import com.test.notification.Model.NotificationTemplate;

public interface TemplateService {

    NotificationTemplate getTemplatedById(String id);
    NotificationTemplate createTemplate(NotificationTemplate notificationTemplate);
}
