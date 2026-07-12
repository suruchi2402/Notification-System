package com.notification.notification_system.producer;

import com.notification.notification_system.entity.Notification;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationProducer {
    private final KafkaTemplate<String, Notification> kafkaTemplate;

    public NotificationProducer(KafkaTemplate<String, Notification> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Notification notification) {

        kafkaTemplate.send("notification-topic", notification);

        System.out.println("Message Published : " + notification);
    }
}
