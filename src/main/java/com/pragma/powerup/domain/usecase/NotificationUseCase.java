package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.INotificationServicePort;
import com.pragma.powerup.domain.spi.IMessagePersistencePort;

public class NotificationUseCase implements INotificationServicePort {
    private final IMessagePersistencePort messagePersistencePort;

    public NotificationUseCase(IMessagePersistencePort messagePersistencePort) {
        this.messagePersistencePort = messagePersistencePort;
    }

    @Override
    public void sendNotification(Long idOrder, String phone) {
        String pin = messagePersistencePort.generatePin();
        String message = String.format("Tu pedido #%d está listo. Usa este PIN para recogerlo: %s",
                idOrder, pin);

        messagePersistencePort.saveNotificationPin(phone, pin);
        messagePersistencePort.sendSmsMessage(phone, message);
    }
}
