package com.notification.notification_system.consumer;
import com.notification.notification_system.entity.Notification;
import com.notification.notification_system.enums.NotificationStatus;
import com.notification.notification_system.event.NotificationEvent;
import com.notification.notification_system.exception.ResourceNotFoundException;
import com.notification.notification_system.factory.NotificationSenderFactory;
import com.notification.notification_system.repository.NotificationRepository;
import com.notification.notification_system.sender.NotificationSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListeners;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    private final NotificationSenderFactory notificationSenderFactory;
    private final NotificationRepository notificationRepository;

    private static final Logger log = LoggerFactory.getLogger(NotificationConsumer.class);
    public NotificationConsumer(NotificationSenderFactory notificationSenderFactory, NotificationRepository notificationRepository){
        this.notificationSenderFactory=notificationSenderFactory;
        this.notificationRepository=notificationRepository;
    }

    @RetryableTopic(attempts = "4",
    backoff = @Backoff(delay = 3000,multiplier = 2.0),
    topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE)
    @KafkaListeners({@KafkaListener(topics  ="notification-topic", groupId="notification-group")})
    public void consume(NotificationEvent event){
        Notification notification = notificationRepository.findById(event.getId()).orElseThrow(()->new ResourceNotFoundException("Notification not found with id: "+ event.getId()));
        NotificationSender sender = notificationSenderFactory.getSender(event.getChannel());
        sender.send(event);
        notification.setStatus(NotificationStatus.SENT);

//       try {
//           NotificationSender sender = notificationSenderFactory.getSender(event.getChannel());
//           sender.send(event);
//           notification.setStatus(NotificationStatus.SENT);
//       }catch (Exception ex){
//           log.error("Failed to send notification with id {}",
//                   event.getId(), ex);
//           notification.setStatus(NotificationStatus.FAILED);
//       }
        notificationRepository.save(notification);

//        System.out.println("Message Recieved: "+ notification);
//        System.out.println(notification.getRecipient());
//        System.out.println(notification.getSubject());
//        System.out.println(notification.getMessage());
//        System.out.println(notification.getChannel());
//        System.out.println(notification.getStatus());
    }
    @DltHandler
    public void handleDlt(NotificationEvent event) {

        log.error("Message moved to DLT for notification id {}", event.getId());

        Notification notification = notificationRepository.findById(event.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Notification not found with id: " + event.getId()));

        notification.setStatus(NotificationStatus.FAILED);

        notificationRepository.save(notification);

        log.info("Notification {} marked as FAILED", event.getId());
    }

}
