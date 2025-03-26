package com.pragma.powerup.domain.api;

public interface INotificationServicePort {
    void sendNotification(Long idOrder, String phone);
    Boolean existPinByPhoneNumber(String phone);
    String findPinByPhoneNumber(String phone);
    void deliverOrder(Long idOrder, String phoneNumber, String pin);
}
