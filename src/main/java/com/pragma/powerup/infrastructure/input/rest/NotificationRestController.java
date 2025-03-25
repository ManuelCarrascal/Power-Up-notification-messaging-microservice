package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.OrderReadyRequestDto;
import com.pragma.powerup.application.handler.INotificationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/notification")
public class NotificationRestController {

    private final INotificationHandler notificationHandler;

    @PostMapping
    public ResponseEntity<Void> sendNotification(OrderReadyRequestDto orderReadyRequestDto) {
        notificationHandler.sendNotification(orderReadyRequestDto);
        return ResponseEntity.ok().build();
    }
}
