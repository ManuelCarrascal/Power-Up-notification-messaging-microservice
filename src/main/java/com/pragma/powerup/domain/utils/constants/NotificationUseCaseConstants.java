package com.pragma.powerup.domain.utils.constants;

public class NotificationUseCaseConstants {
    private NotificationUseCaseConstants() {
    }
    public static final int MIN_PIN = 1000;
    public static final int MAX_PIN_OFFSET = 9000;

    public static final String NOTIFICATION_MESSAGE_TEMPLATE = "Su pedido #%d está listo. Por favor recoja su orden presentando el siguiente código PIN: %s";

    public static final String PIN_NOT_FOUND_MESSAGE = "No se encontró un PIN asociado a este número telefónico";
    public static final String INVALID_PIN_MESSAGE = "El PIN ingresado es incorrecto";
}