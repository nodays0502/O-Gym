package com.B305.ogym.controller.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class GetUserInfoRequestListDto {
    @NotEmpty
    private List<String> req;
}
