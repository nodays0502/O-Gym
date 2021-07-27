package com.B305.ogym.controller.dto;

import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class GetUserInfoRequestListDto {
    private List<String> req;
}
