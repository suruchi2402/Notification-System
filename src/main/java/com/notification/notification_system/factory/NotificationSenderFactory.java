package com.notification.notification_system.factory;

import com.notification.notification_system.enums.NotificationChannel;
import com.notification.notification_system.sender.NotificationSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationSenderFactory {
    private final List<NotificationSender>senders;
    public NotificationSenderFactory(List<NotificationSender> senders){
        this.senders=senders;
    }
    public NotificationSender getSender(NotificationChannel channel){
        for(NotificationSender sender: senders){
            if(sender.getChannel()==channel){
                return sender;
            }
        }
        throw new IllegalArgumentException("No Sender Found For Channel "+ channel);
    }
}
