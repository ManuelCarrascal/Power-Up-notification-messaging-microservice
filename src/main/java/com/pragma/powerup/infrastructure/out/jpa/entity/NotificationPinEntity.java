package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "notification_pin")
@IdClass(NotificationPinIdEntity.class)
public class NotificationPinEntity
{
    @Id
    private String phoneNumber;
    @Id
    private String pin;
}
