package com.test.notification.Bean;

import com.test.notification.Service.Impl.AppNotificationServiceImpl;
import com.test.notification.Service.Impl.EmailNotificationServiceImpl;
import com.test.notification.Service.Impl.NotificationServiceImpl;
import com.test.notification.Service.Impl.SmsNotificationServiceImpl;
import com.test.notification.Service.NotificationSender;
import com.test.notification.Service.NotificationService;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;

public class NotificationBean {

    @Bean
    public NotificationSender emailNotificationServiceImpl(JavaMailSender mailSender) {
        var emailNotificationService = new EmailNotificationServiceImpl();
        emailNotificationService.setMailSender(mailSender);
        return emailNotificationService;
    }

    @Bean
    public NotificationSender smsNotificationServiceImpl() {
        //setter here
        return new SmsNotificationServiceImpl();
    }

    @Bean
    public NotificationSender appNotificationServiceImpl() {
        //setter here
        return new AppNotificationServiceImpl();
    }

    @Bean
    public NotificationService notificationService() {
        return new NotificationServiceImpl();
    }
}
