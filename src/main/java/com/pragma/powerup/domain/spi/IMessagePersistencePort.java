package com.pragma.powerup.domain.spi;

public interface IMessagePersistencePort {
    void sendSmsMessage(String phoneNumber, String message);

    void saveNotificationPin(String phoneNumber, String pin);
}