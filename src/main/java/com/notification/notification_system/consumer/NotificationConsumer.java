package com.notification.notification_system.consumer;
import com.notification.notification_system.entity.Notification;
import com.notification.notification_system.enums.NotificationStatus;
import com.notification.notification_system.factory.NotificationSenderFactory;
import com.notification.notification_system.repository.NotificationRepository;
import com.notification.notification_system.sender.NotificationSender;
import org.springframework.kafka.annotation.KafkaListeners;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    private final NotificationSenderFactory notificationSenderFactory;
    private final NotificationRepository notificationRepository;
    public NotificationConsumer(NotificationSenderFactory notificationSenderFactory, NotificationRepository notificationRepository){
        this.notificationSenderFactory=notificationSenderFactory;
        this.notificationRepository=notificationRepository;
    }
    @KafkaListeners({@KafkaListener(topics  ="notification-topic", groupId="notification-group")})
    public void consume(Notification notification){
        NotificationSender sender = notificationSenderFactory.getSender(notification.getChannel());
        sender.send(notification);
        notification.setStatus(NotificationStatus.SENT);
        notificationRepository.save(notification);

//        System.out.println("Message Recieved: "+ notification);
//        System.out.println(notification.getRecipient());
//        System.out.println(notification.getSubject());
//        System.out.println(notification.getMessage());
//        System.out.println(notification.getChannel());
//        System.out.println(notification.getStatus());
    }
}
