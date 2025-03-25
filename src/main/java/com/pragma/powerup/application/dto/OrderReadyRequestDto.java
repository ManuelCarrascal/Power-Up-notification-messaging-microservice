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
@Schema(description = "DTO for order notification request")
public class OrderReadyRequestDto {

    @Schema(description = "ID of the order that is ready", example = "1234", required = true)
    private Long idOrder;

    @Schema(description = "Customer's phone number to send notification", example = "+573001234567", required = true)
    private String phone;
}