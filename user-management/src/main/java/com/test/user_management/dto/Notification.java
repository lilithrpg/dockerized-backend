package com.test.user_management.dto;

import lombok.Builder;

import java.util.Map;

@Builder
public class Notification {
    private String notificationId;
    private String recipient;
    private Map<String, String> entries;
}
