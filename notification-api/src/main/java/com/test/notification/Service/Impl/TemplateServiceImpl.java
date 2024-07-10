package com.test.notification.Service.Impl;

import com.test.notification.Model.NotificationTemplate;
import com.test.notification.Service.TemplateService;
import org.springframework.stereotype.Service;

@Service
public class TemplateServiceImpl implements TemplateService {
    @Override
    public NotificationTemplate getTemplatedById(String id) {
        // call get by template id in mongodb
        return new NotificationTemplate();
    }

    @Override
    public NotificationTemplate createTemplate(NotificationTemplate notificationTemplate) {
        // .save() here
        return new NotificationTemplate();
    }
}
