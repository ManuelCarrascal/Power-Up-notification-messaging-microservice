package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.spi.IMessagePersistencePort;
import com.pragma.powerup.domain.spi.INotificationPersistencePort;
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

    @Mock
    private INotificationPersistencePort notificationPersistencePort;

    private NotificationUseCase notificationUseCase;

    @BeforeEach
    void setUp() {
        notificationUseCase = new NotificationUseCase(messagePersistencePort, notificationPersistencePort);
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
    void existPinByPhoneNumber_whenPinExists_shouldReturnTrue() {
        String phone = "+573001234567";
        when(notificationPersistencePort.findPinByPhoneNumber(phone)).thenReturn("1234");

        Boolean result = notificationUseCase.existPinByPhoneNumber(phone);

        assertTrue(result);
        verify(notificationPersistencePort).findPinByPhoneNumber(phone);
    }

    @Test
    void existPinByPhoneNumber_whenPinDoesNotExist_shouldReturnFalse() {
        String phone = "+573001234567";
        when(notificationPersistencePort.findPinByPhoneNumber(phone)).thenReturn(null);

        Boolean result = notificationUseCase.existPinByPhoneNumber(phone);

        assertFalse(result);
        verify(notificationPersistencePort).findPinByPhoneNumber(phone);
    }

    @Test
    void findPinByPhoneNumber_shouldReturnPin() {
        String phone = "+573001234567";
        String expectedPin = "1234";
        when(notificationPersistencePort.findPinByPhoneNumber(phone)).thenReturn(expectedPin);

        String result = notificationUseCase.findPinByPhoneNumber(phone);

        assertEquals(expectedPin, result);
        verify(notificationPersistencePort).findPinByPhoneNumber(phone);
    }

    @Test
    void deliverOrder_whenValidPin_shouldNotThrowException() {
        Long orderId = 12345L;
        String phone = "+573001234567";
        String pin = "1234";
        when(notificationPersistencePort.findPinByPhoneNumber(phone)).thenReturn(pin);

        assertDoesNotThrow(() -> notificationUseCase.deliverOrder(orderId, phone, pin));
        verify(notificationPersistencePort).findPinByPhoneNumber(phone);
    }

    @Test
    void deliverOrder_whenPinNotFound_shouldThrowException() {
        Long orderId = 12345L;
        String phone = "+573001234567";
        String pin = "1234";
        when(notificationPersistencePort.findPinByPhoneNumber(phone)).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> notificationUseCase.deliverOrder(orderId, phone, pin));
        assertEquals(NotificationUseCaseConstants.PIN_NOT_FOUND_MESSAGE, exception.getMessage());
        verify(notificationPersistencePort).findPinByPhoneNumber(phone);
    }

    @Test
    void deliverOrder_whenInvalidPin_shouldThrowException() {
        Long orderId = 12345L;
        String phone = "+573001234567";
        String pin = "1234";
        String storedPin = "5678";
        when(notificationPersistencePort.findPinByPhoneNumber(phone)).thenReturn(storedPin);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> notificationUseCase.deliverOrder(orderId, phone, pin));
        assertEquals(NotificationUseCaseConstants.INVALID_PIN_MESSAGE, exception.getMessage());
        verify(notificationPersistencePort).findPinByPhoneNumber(phone);
    }

    @Test
    void generatePin_shouldCreateFourDigitPin() {
        try {
            java.lang.reflect.Method generatePinMethod = NotificationUseCase.class.getDeclaredMethod("generatePin");
            generatePinMethod.setAccessible(true);

            for (int i = 0; i < 100; i++) {
                String pin = (String) generatePinMethod.invoke(notificationUseCase);

                assertEquals(4, pin.length());
                int pinValue = Integer.parseInt(pin);
                assertTrue(pinValue >= NotificationUseCaseConstants.MIN_PIN);
                assertTrue(pinValue < NotificationUseCaseConstants.MIN_PIN + NotificationUseCaseConstants.MAX_PIN_OFFSET);
            }
        } catch (Exception e) {
            fail("Failed to test generatePin: " + e.getMessage());
        }
    }
}