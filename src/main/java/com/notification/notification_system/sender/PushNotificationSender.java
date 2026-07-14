package com.notification.notification_system.sender;

import com.notification.notification_system.entity.Notification;
import com.notification.notification_system.enums.NotificationChannel;
import com.notification.notification_system.event.NotificationEvent;
import org.springframework.stereotype.Service;

@Service
public class PushNotificationSender implements NotificationSender{
    @Override
    public NotificationChannel getChannel() {
        return NotificationChannel.PUSH;
    }
    @Override
    public void send(NotificationEvent event) {
        throw new RuntimeException("Push server down");
//        System.out.println("Sending Push Notification to "+ event.getRecipient());
    }
}
