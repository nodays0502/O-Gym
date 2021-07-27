package com.B305.ogym.controller.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UpdateStudentRequestDto {

    @NotEmpty
    List<Integer> monthlyHeights;

    @NotEmpty
    List<Integer> monthlyWeights;

}
