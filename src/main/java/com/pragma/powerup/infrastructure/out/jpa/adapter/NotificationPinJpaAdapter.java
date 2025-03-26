package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.spi.INotificationPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.entity.NotificationPinEntity;
import com.pragma.powerup.infrastructure.out.jpa.repository.INotificationPinRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotificationPinJpaAdapter implements INotificationPersistencePort {
    private final INotificationPinRepository notificationPinRepository;

    @Override
    public String findPinByPhoneNumber(String phoneNumber) {
        NotificationPinEntity entity = notificationPinRepository.findByPhoneNumber(phoneNumber);
        return entity != null ? entity.getPin() : null;
    }


}
