package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.INotificationServicePort;
import com.pragma.powerup.domain.spi.IMessagePersistencePort;
import com.pragma.powerup.domain.utils.constants.NotificationUseCaseConstants;

import java.util.Random;

public class NotificationUseCase implements INotificationServicePort {
    private final IMessagePersistencePort messagePersistencePort;
    private final Random random = new Random();

    public NotificationUseCase(IMessagePersistencePort messagePersistencePort) {
        this.messagePersistencePort = messagePersistencePort;
    }

    @Override
    public void sendNotification(Long idOrder, String phone) {
        String pin = generatePin();
        String message = String.format(NotificationUseCaseConstants.NOTIFICATION_MESSAGE_TEMPLATE,
                idOrder, pin);

        messagePersistencePort.saveNotificationPin(phone, pin);
        messagePersistencePort.sendSmsMessage(phone, message);
    }

    private String generatePin() {
        int pin = NotificationUseCaseConstants.MIN_PIN +
                random.nextInt(NotificationUseCaseConstants.MAX_PIN_OFFSET);
        return String.valueOf(pin);
    }
}