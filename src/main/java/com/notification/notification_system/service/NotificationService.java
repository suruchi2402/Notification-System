package com.notification.notification_system.service;

import com.notification.notification_system.dto.NotificationRequest;
import com.notification.notification_system.entity.Notification;
import com.notification.notification_system.enums.NotificationStatus;
import com.notification.notification_system.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {
    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository){
        this.repository=repository;
    }

    public Notification createNotification(NotificationRequest request){
        Notification notification = new Notification();
        notification.setRecipient(request.getRecipient());
        notification.setSubject(request.getSubject());
        notification.setMessage(request.getMessage());
        notification.setChannel(request.getChannel());

        notification.setStatus(NotificationStatus.PENDING);

        LocalDateTime now = LocalDateTime.now();
        notification.setCreatedAt(now);
        notification.setUpdatedAt(now);
        return repository.save(notification);
    }
}
