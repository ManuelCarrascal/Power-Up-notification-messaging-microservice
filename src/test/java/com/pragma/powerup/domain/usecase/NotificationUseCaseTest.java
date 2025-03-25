package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.spi.IMessagePersistencePort;
import com.pragma.powerup.domain.utils.constants.NotificationUseCaseConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationUseCaseTest {

    @Mock
    private IMessagePersistencePort messagePersistencePort;

    private NotificationUseCase notificationUseCase;

    @BeforeEach
    void setUp() {
        notificationUseCase = new NotificationUseCase(messagePersistencePort);
    }

    @Test
    void sendNotification_shouldGeneratePinAndSendNotification() {
        Long orderId = 12345L;
        String phone = "+573001234567";

        notificationUseCase.sendNotification(orderId, phone);

        ArgumentCaptor<String> pinCaptor = ArgumentCaptor.forClass(String.class);
        verify(messagePersistencePort).saveNotificationPin(eq(phone), pinCaptor.capture());

        String capturedPin = pinCaptor.getValue();
        assertNotNull(capturedPin);

        int pinValue = Integer.parseInt(capturedPin);
        assertTrue(pinValue >= NotificationUseCaseConstants.MIN_PIN);
        assertTrue(pinValue < NotificationUseCaseConstants.MIN_PIN + NotificationUseCaseConstants.MAX_PIN_OFFSET);

        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(messagePersistencePort).sendSmsMessage(eq(phone), messageCaptor.capture());

        String capturedMessage = messageCaptor.getValue();
        assertTrue(capturedMessage.contains(orderId.toString()));
        assertTrue(capturedMessage.contains(capturedPin));
    }

    @Test
    void generatePin_shouldCreateSixDigitPin() {
        try {
            java.lang.reflect.Method generatePinMethod = NotificationUseCase.class.getDeclaredMethod("generatePin");
            generatePinMethod.setAccessible(true);

            for (int i = 0; i < 100; i++) {
                String pin = (String) generatePinMethod.invoke(notificationUseCase);

                assertEquals(6, pin.length());
                int pinValue = Integer.parseInt(pin);
                assertTrue(pinValue >= NotificationUseCaseConstants.MIN_PIN);
                assertTrue(pinValue < NotificationUseCaseConstants.MIN_PIN + NotificationUseCaseConstants.MAX_PIN_OFFSET);
            }
        } catch (Exception e) {
            fail("Failed to test generatePin: " + e.getMessage());
        }
    }
}