package com.test.notification.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class NotificationTemplate extends NotificationCommon {

    private Template template;

    public NotificationTemplate() {}
}
