package com.notification.notification_system.sender;

import com.notification.notification_system.entity.Notification;
import com.notification.notification_system.enums.NotificationChannel;
import org.springframework.stereotype.Service;

@Service
public class SmsNotificationSender implements NotificationSender{
    @Override
    public NotificationChannel getChannel() {
        return NotificationChannel.PUSH;
    }
    @Override
    public void send(Notification notification){
        System.out.println("Sending SMS Notification to "+ notification.getRecipient());
    }
}
