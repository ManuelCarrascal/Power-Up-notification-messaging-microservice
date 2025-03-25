package com.pragma.powerup.application.utils.constants.openapi;

public class OpenApiOrderReadyRequestDto {
    private OpenApiOrderReadyRequestDto() {
    }

    public static final String DTO_DESCRIPTION = "DTO for order notification request";

    public static final String ID_ORDER_DESCRIPTION = "ID of the order that is ready";
    public static final String ID_ORDER_EXAMPLE = "1234";

    public static final String PHONE_DESCRIPTION = "Customer's phone number to send notification";
    public static final String PHONE_EXAMPLE = "+573001234567";
}