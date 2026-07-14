package com.notification.notification_system.sender;

import com.notification.notification_system.entity.Notification;
import com.notification.notification_system.enums.NotificationChannel;
import com.notification.notification_system.event.NotificationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationSender implements NotificationSender{

    private static final Logger log= LoggerFactory.getLogger(EmailNotificationSender.class);
    @Override
    public NotificationChannel getChannel() {
        return NotificationChannel.EMAIL;
    }

    @Override
    public void send(NotificationEvent event){
        log.info("Sending Email Notification to {}", event.getRecipient());
    }
}
