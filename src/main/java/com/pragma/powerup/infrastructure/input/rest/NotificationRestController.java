package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.DeliverOrderRequestDto;
import com.pragma.powerup.application.dto.OrderReadyRequestDto;
import com.pragma.powerup.application.handler.INotificationHandler;
import com.pragma.powerup.infrastructure.utils.constants.openapi.OpenApiNotificationRestController;
import com.pragma.powerup.infrastructure.utils.constants.openapi.ResponseCodes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(OpenApiNotificationRestController.REQUEST_MAPPING_PATH)
@Tag(name = OpenApiNotificationRestController.TAG_NAME,
        description = OpenApiNotificationRestController.TAG_DESCRIPTION)
public class NotificationRestController {

    private final INotificationHandler notificationHandler;

    @Operation(
            summary = OpenApiNotificationRestController.OPERATION_SUMMARY,
            description = OpenApiNotificationRestController.OPERATION_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_OK,
                    description = OpenApiNotificationRestController.RESPONSE_200_DESCRIPTION),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_BAD_REQUEST,
                    description = OpenApiNotificationRestController.RESPONSE_400_DESCRIPTION,
                    content = @Content),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_INTERNAL_SERVER_ERROR,
                    description = OpenApiNotificationRestController.RESPONSE_500_DESCRIPTION,
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> sendNotification(@RequestBody OrderReadyRequestDto orderReadyRequestDto) {
        notificationHandler.sendNotification(orderReadyRequestDto);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = OpenApiNotificationRestController.EXISTS_PIN_OPERATION_SUMMARY,
            description = OpenApiNotificationRestController.EXISTS_PIN_OPERATION_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_OK,
                    description = OpenApiNotificationRestController.EXISTS_PIN_RESPONSE_200_DESCRIPTION),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_BAD_REQUEST,
                    description = OpenApiNotificationRestController.EXISTS_PIN_RESPONSE_400_DESCRIPTION,
                    content = @Content),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_INTERNAL_SERVER_ERROR,
                    description = OpenApiNotificationRestController.RESPONSE_500_DESCRIPTION,
                    content = @Content)
    })
    @PostMapping("/exists-pin")
    public ResponseEntity<Boolean> existsPinByPhoneNumber(@RequestBody String phoneNumber) {
        return ResponseEntity.ok(notificationHandler.existsPinByPhoneNumber(phoneNumber));
    }

    @Operation(
            summary = OpenApiNotificationRestController.FIND_PIN_OPERATION_SUMMARY,
            description = OpenApiNotificationRestController.FIND_PIN_OPERATION_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_OK,
                    description = OpenApiNotificationRestController.FIND_PIN_RESPONSE_200_DESCRIPTION),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_BAD_REQUEST,
                    description = OpenApiNotificationRestController.FIND_PIN_RESPONSE_400_DESCRIPTION,
                    content = @Content),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_INTERNAL_SERVER_ERROR,
                    description = OpenApiNotificationRestController.RESPONSE_500_DESCRIPTION,
                    content = @Content)
    })
    @PostMapping("/find-pin")
    public ResponseEntity<String> findPinByPhoneNumber(@RequestBody String phoneNumber) {
        return ResponseEntity.ok(notificationHandler.findPinByPhoneNumber(phoneNumber));
    }

    @Operation(
            summary = OpenApiNotificationRestController.DELIVER_ORDER_OPERATION_SUMMARY,
            description = OpenApiNotificationRestController.DELIVER_ORDER_OPERATION_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_OK,
                    description = OpenApiNotificationRestController.DELIVER_ORDER_RESPONSE_200_DESCRIPTION),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_BAD_REQUEST,
                    description = OpenApiNotificationRestController.DELIVER_ORDER_RESPONSE_400_DESCRIPTION,
                    content = @Content),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_INTERNAL_SERVER_ERROR,
                    description = OpenApiNotificationRestController.RESPONSE_500_DESCRIPTION,
                    content = @Content)
    })
    @PostMapping("/deliver-order")
    public ResponseEntity<Void> deliverOrder(@RequestBody DeliverOrderRequestDto deliverOrderRequestDto) {
        notificationHandler.deliverOrder(deliverOrderRequestDto);
        return ResponseEntity.ok().build();
    }
}