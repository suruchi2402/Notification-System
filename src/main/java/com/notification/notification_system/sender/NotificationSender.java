package com.notification.notification_system.sender;

import com.notification.notification_system.entity.Notification;
import com.notification.notification_system.enums.NotificationChannel;
import com.notification.notification_system.event.NotificationEvent;

public interface NotificationSender {
    NotificationChannel getChannel();
    void send(NotificationEvent event);
}
