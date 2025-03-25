package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.entity.NotificationPinEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INotificationPinRepository extends JpaRepository<NotificationPinEntity, Long> {
}
