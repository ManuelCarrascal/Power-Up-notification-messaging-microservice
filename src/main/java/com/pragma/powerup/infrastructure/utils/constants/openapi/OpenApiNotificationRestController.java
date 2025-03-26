package com.pragma.powerup.infrastructure.utils.constants.openapi;

public class OpenApiNotificationRestController {
    private OpenApiNotificationRestController() {
    }

    public static final String TAG_NAME = "Notification Controller";
    public static final String TAG_DESCRIPTION = "API for sending notifications to customers";

    public static final String REQUEST_MAPPING_PATH = "api/v1/notification";

    public static final String OPERATION_SUMMARY = "Send order ready notification";
    public static final String OPERATION_DESCRIPTION =
            "Sends a notification with a PIN to the customer's phone when their order is ready";

    public static final String EXISTS_PIN_OPERATION_SUMMARY = "Check if PIN exists for a phone number";
    public static final String EXISTS_PIN_OPERATION_DESCRIPTION = "Validates if a security PIN exists for the provided customer phone number";
    public static final String EXISTS_PIN_RESPONSE_200_DESCRIPTION = "Successfully checked PIN existence";
    public static final String EXISTS_PIN_RESPONSE_400_DESCRIPTION = "Invalid phone number format";

    public static final String FIND_PIN_OPERATION_SUMMARY = "Find PIN for a phone number";
    public static final String FIND_PIN_OPERATION_DESCRIPTION = "Retrieves the security PIN associated with the provided customer phone number";
    public static final String FIND_PIN_RESPONSE_200_DESCRIPTION = "PIN retrieved successfully";
    public static final String FIND_PIN_RESPONSE_400_DESCRIPTION = "Invalid phone number format";

    public static final String DELIVER_ORDER_OPERATION_SUMMARY = "Validate PIN for order delivery";
    public static final String DELIVER_ORDER_OPERATION_DESCRIPTION = "Validates the security PIN provided by customer for order delivery confirmation";
    public static final String DELIVER_ORDER_RESPONSE_200_DESCRIPTION = "PIN validated successfully";
    public static final String DELIVER_ORDER_RESPONSE_400_DESCRIPTION = "Invalid PIN or phone number";

    // Common response descriptions
    public static final String RESPONSE_200_DESCRIPTION = "Notification sent successfully";
    public static final String RESPONSE_400_DESCRIPTION = "Invalid request data";
    public static final String RESPONSE_500_DESCRIPTION = "Internal server error";
}