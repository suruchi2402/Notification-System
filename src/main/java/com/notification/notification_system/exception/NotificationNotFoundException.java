package com.notification.notification_system.exception;

public class NotificationNotFoundException extends RuntimeException{
    public NotificationNotFoundException(Long id){
        super("Notification Not Found with id "+id);
    }
}
