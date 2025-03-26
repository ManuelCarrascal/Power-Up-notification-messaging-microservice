package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.DeliverOrderRequestDto;
import com.pragma.powerup.application.dto.OrderReadyRequestDto;
import com.pragma.powerup.application.handler.INotificationHandler;
import com.pragma.powerup.domain.api.INotificationServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationHandler implements INotificationHandler {

    private final INotificationServicePort notificationService;

    @Override
    public void sendNotification(OrderReadyRequestDto orderReadyRequestDto) {
        notificationService.sendNotification(orderReadyRequestDto.getIdOrder(), orderReadyRequestDto.getPhone());
    }

    @Override
    public Boolean existsPinByPhoneNumber(String phoneNumber) {
        return notificationService.existPinByPhoneNumber(phoneNumber);
    }

    @Override
    public String findPinByPhoneNumber(String phoneNumber) {
        return notificationService.findPinByPhoneNumber(phoneNumber);
    }

    @Override
    public void deliverOrder(DeliverOrderRequestDto deliverOrderRequestDto) {
        notificationService.deliverOrder(
                deliverOrderRequestDto.getIdOrder(),
                deliverOrderRequestDto.getPhoneNumber(),
                deliverOrderRequestDto.getPin()
        );
    }
}
