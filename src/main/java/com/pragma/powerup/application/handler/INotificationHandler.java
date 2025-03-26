package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.DeliverOrderRequestDto;
import com.pragma.powerup.application.dto.OrderReadyRequestDto;

public interface INotificationHandler {
    void sendNotification(OrderReadyRequestDto orderReadyRequestDto);

    Boolean existsPinByPhoneNumber(String phoneNumber);

    String findPinByPhoneNumber(String phoneNumber);

    void deliverOrder(DeliverOrderRequestDto deliverOrderRequestDto);
}
