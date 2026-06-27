package com.notification.notification_system.controller;

import com.notification.notification_system.dto.NotificationRequest;
import com.notification.notification_system.service.NotificationService;
import com.notification.notification_system.entity.Notification;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService){
        this.notificationService=notificationService;

    }

    @PostMapping
    public Notification createNotification(@RequestBody NotificationRequest request){
        return notificationService.createNotification(request);

    }



}
