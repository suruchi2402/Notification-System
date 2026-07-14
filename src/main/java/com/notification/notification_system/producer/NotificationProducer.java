package com.notification.notification_system.producer;

import com.notification.notification_system.event.NotificationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationProducer {
    private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;
    private static final Logger log = LoggerFactory.getLogger(NotificationProducer.class);
    public NotificationProducer(KafkaTemplate<String, NotificationEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(NotificationEvent event) {

        kafkaTemplate.send("notification-topic", event);

       log.info("Message Published : {}" , event);
    }
}
