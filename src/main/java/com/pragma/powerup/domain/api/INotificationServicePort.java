package com.pragma.powerup.domain.api;

public interface INotificationServicePort {
    void sendNotification(Long idOrder, String phone);
}
