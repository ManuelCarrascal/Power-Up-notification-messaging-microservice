package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.OrderReadyRequestDto;

public interface INotificationHandler {
    void sendNotification(OrderReadyRequestDto orderReadyRequestDto);
}
