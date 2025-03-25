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

    public static final String RESPONSE_200_DESCRIPTION = "Notification sent successfully";
    public static final String RESPONSE_400_DESCRIPTION = "Invalid request data";
    public static final String RESPONSE_500_DESCRIPTION = "Internal server error";
}