package com.pragma.powerup.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliverOrderRequestDto {
    private Long idOrder;
    private String phoneNumber;
    private String pin;
}