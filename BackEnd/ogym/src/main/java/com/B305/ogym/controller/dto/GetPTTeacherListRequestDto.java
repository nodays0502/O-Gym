package com.B305.ogym.controller.dto;

import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class GetPTTeacherListRequestDto {
    @NotEmpty
    private List<Map<String,String>> filter;
}
