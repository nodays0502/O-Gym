package com.B305.ogym.controller.dto;

import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class GetMyPTStudentHealthListResponseDto {
    private List<Map<String,Object>> studentHealthList;
}
