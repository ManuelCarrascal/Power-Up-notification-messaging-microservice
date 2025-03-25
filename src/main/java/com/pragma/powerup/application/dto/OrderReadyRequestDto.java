package com.pragma.powerup.application.dto;

import com.pragma.powerup.application.utils.constants.openapi.OpenApiOrderReadyRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = OpenApiOrderReadyRequestDto.DTO_DESCRIPTION)
public class OrderReadyRequestDto {

    @Schema(
            description = OpenApiOrderReadyRequestDto.ID_ORDER_DESCRIPTION,
            example = OpenApiOrderReadyRequestDto.ID_ORDER_EXAMPLE,
            required = true
    )
    private Long idOrder;

    @Schema(
            description = OpenApiOrderReadyRequestDto.PHONE_DESCRIPTION,
            example = OpenApiOrderReadyRequestDto.PHONE_EXAMPLE,
            required = true
    )
    private String phone;
}