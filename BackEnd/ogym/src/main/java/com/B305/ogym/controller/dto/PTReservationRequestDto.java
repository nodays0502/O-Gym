package com.B305.ogym.controller.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class PTReservationRequestDto {
    @NotNull
    private int ptTeacherId;
    @NotEmpty
    private LocalDateTime reservationTime;
}
