package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.spi.IMessagePersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.entity.NotificationPinEntity;
import com.pragma.powerup.infrastructure.out.jpa.repository.INotificationPinRepository;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.Random;

@RequiredArgsConstructor
public class MessageJpaAdapter implements IMessagePersistencePort {

    private final INotificationPinRepository notificationPinRepository;

    @Value("${twilio.phone-number}")
    private String twilioPhoneNumber;

    @Override
    public void sendSmsMessage(String phoneNumber, String messageContent) {
        Message.creator(
                        new PhoneNumber(phoneNumber),
                        new PhoneNumber(twilioPhoneNumber),
                        messageContent)
                .create();
    }

    @Override
    public String generatePin() {
        Random random = new Random();
        int pin = 100000 + random.nextInt(900000); // 6-digit pin
        return String.valueOf(pin);
    }

    @Override
    public void saveNotificationPin(String phoneNumber, String pin) {
        NotificationPinEntity notificationPin = NotificationPinEntity.builder()
                .phoneNumber(phoneNumber)
                .pin(pin)
                .build();
        notificationPinRepository.save(notificationPin);
    }
}