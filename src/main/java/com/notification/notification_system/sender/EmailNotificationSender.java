package com.notification.notification_system.sender;

import com.notification.notification_system.entity.Notification;
import com.notification.notification_system.enums.NotificationChannel;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationSender implements NotificationSender{

    @Override
    public NotificationChannel getChannel() {
        return NotificationChannel.EMAIL;
    }

    @Override
    public void send(Notification notification){
        System.out.println("Sending Email Notification to "+ notification.getRecipient());
    }
}
