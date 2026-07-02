package com.notification.notification_system.service;

import com.notification.notification_system.dto.NotificationRequest;
import com.notification.notification_system.entity.Notification;
import com.notification.notification_system.enums.NotificationStatus;
import com.notification.notification_system.exception.NotificationNotFoundException;
import com.notification.notification_system.factory.NotificationSenderFactory;
import com.notification.notification_system.repository.NotificationRepository;
import com.notification.notification_system.sender.NotificationSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class NotificationService {
    private final NotificationRepository repository;
    private final NotificationSenderFactory senderFactory;

    public NotificationService(NotificationRepository repository, NotificationSenderFactory senderFactory){
        this.repository=repository;
        this.senderFactory=senderFactory;
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
        Notification savedNotification = repository.save(notification);
        NotificationSender sender = senderFactory.getSender(request.getChannel());
        sender.send(savedNotification);
        return savedNotification;
    }

    public List<Notification> getALlNotification(){
        return repository.findAll();
    }

    public Notification getNotificationBYId(Long id){
//        Optional<Notification> optionalNotification=repository.findById(id);
        return repository.findById(id).orElseThrow(()-> new NotificationNotFoundException(id));
    }

    public Notification updateNotificationBYId(Long id, NotificationRequest request){
        Notification notification = getNotificationBYId(id);
        notification.setRecipient(request.getRecipient());
        notification.setSubject(request.getSubject());
        notification.setMessage(request.getMessage());
        notification.setChannel(request.getChannel());
        notification.setUpdatedAt(LocalDateTime.now());
        return repository.save(notification);
    }

    public void deleteNotificationById(Long id){
        Notification notification = getNotificationBYId(id);
        repository.delete(notification);
    }
}
