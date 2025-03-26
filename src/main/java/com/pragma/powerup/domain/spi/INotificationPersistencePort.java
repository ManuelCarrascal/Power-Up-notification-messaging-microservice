package com.pragma.powerup.domain.spi;


public interface INotificationPersistencePort {
    String findPinByPhoneNumber(String phoneNumber);
}
