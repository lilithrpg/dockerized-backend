package com.test.notification.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class NotificationRequest extends NotificationCommon {

    private String recipient;
    private Map<String, String> entries;

    public NotificationRequest() {}
}
