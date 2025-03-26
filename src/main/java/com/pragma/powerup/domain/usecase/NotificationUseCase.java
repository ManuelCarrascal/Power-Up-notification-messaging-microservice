package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.INotificationServicePort;
import com.pragma.powerup.domain.spi.IMessagePersistencePort;
import com.pragma.powerup.domain.spi.INotificationPersistencePort;
import com.pragma.powerup.domain.utils.constants.NotificationUseCaseConstants;

import java.util.Random;

public class NotificationUseCase implements INotificationServicePort {
    private final IMessagePersistencePort messagePersistencePort;
    private final INotificationPersistencePort notificationPersistencePort;
    private final Random random = new Random();

    public NotificationUseCase(IMessagePersistencePort messagePersistencePort, INotificationPersistencePort notificationPersistencePort) {
        this.messagePersistencePort = messagePersistencePort;
        this.notificationPersistencePort = notificationPersistencePort;
    }

    @Override
    public void sendNotification(Long idOrder, String phone) {
        String pin = generatePin();
        String message = String.format(NotificationUseCaseConstants.NOTIFICATION_MESSAGE_TEMPLATE,
                idOrder, pin);

        messagePersistencePort.saveNotificationPin(phone, pin);
        messagePersistencePort.sendSmsMessage(phone, message);
    }

    @Override
    public Boolean existPinByPhoneNumber(String phone) {
        return notificationPersistencePort.findPinByPhoneNumber(phone) != null;
    }

    @Override
    public String findPinByPhoneNumber(String phone) {
        return notificationPersistencePort.findPinByPhoneNumber(phone);
    }

    @Override
    public void deliverOrder(Long idOrder, String phoneNumber, String pin) {
        String storedPin = notificationPersistencePort.findPinByPhoneNumber(phoneNumber);

        if (storedPin == null) {
            throw new IllegalArgumentException(NotificationUseCaseConstants.PIN_NOT_FOUND_MESSAGE);
        }

        if (!storedPin.equals(pin)) {
            throw new IllegalArgumentException(NotificationUseCaseConstants.INVALID_PIN_MESSAGE);
        }
    }

    private String generatePin() {
        int pin = NotificationUseCaseConstants.MIN_PIN +
                random.nextInt(NotificationUseCaseConstants.MAX_PIN_OFFSET);
        return String.valueOf(pin);
    }
}