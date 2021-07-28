package com.B305.ogym.controller.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class PTReservationCancelRequestDto {
    @NotNull
    private int reservationId;
}
