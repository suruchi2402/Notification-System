package com.notification.notification_system.controller;

import com.notification.notification_system.dto.NotificationRequest;
import com.notification.notification_system.service.NotificationService;
import com.notification.notification_system.entity.Notification;
import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService){
        this.notificationService=notificationService;

    }

    @PostMapping
    public Notification createNotification(@Valid @RequestBody NotificationRequest request){
        return notificationService.createNotification(request);
    }

    @GetMapping
    public List<Notification> getAllNotification(){
        return notificationService.getALlNotification();
    }

    @GetMapping("/{id}")
    public Notification getNotificationById(@PathVariable Long id){
        return notificationService.getNotificationBYId(id);

    }

    @PutMapping("/{id}")
    public Notification updateNotificationBYId(@PathVariable Long id,@Valid @RequestBody NotificationRequest request){
        return notificationService.updateNotificationBYId(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotificationById(@PathVariable Long id){
        notificationService.deleteNotificationById(id);
        return ResponseEntity.noContent().build();
    }
}
