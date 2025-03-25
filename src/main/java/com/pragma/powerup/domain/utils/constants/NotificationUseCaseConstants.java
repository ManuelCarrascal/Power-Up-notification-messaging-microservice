package com.pragma.powerup.domain.utils.constants;

public class NotificationUseCaseConstants {
    private NotificationUseCaseConstants() {
    }

    public static final int MIN_PIN = 100000;
    public static final int MAX_PIN_OFFSET = 900000;
    public static final String NOTIFICATION_MESSAGE_TEMPLATE = "Tu pedido #%d está listo. Usa este PIN para recogerlo: %s";
}