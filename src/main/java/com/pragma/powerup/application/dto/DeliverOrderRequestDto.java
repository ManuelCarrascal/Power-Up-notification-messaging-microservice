package com.pragma.powerup.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for order delivery PIN validation")
public class DeliverOrderRequestDto {

    @Schema(description = "Order identifier", example = "1")
    private Long idOrder;

    @Schema(description = "Customer phone number", example = "3001234567")
    private String phoneNumber;

    @Schema(description = "Security PIN code to validate order delivery", example = "1234")
    private String pin;
}