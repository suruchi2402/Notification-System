package com.notification.notification_system.exception;

import org.springframework.validation.BindingResult;

public class MethodArgumentNotValidException extends Exception {
    private BindingResult bindingResult;
    public MethodArgumentNotValidException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
