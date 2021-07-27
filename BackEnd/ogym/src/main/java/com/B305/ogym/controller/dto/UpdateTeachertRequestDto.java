package com.B305.ogym.controller.dto;

import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UpdateTeachertRequestDto {
    @NotBlank
    private String major;

    @NotEmpty
    private List<Map<String,String>>certificates;

    @NotEmpty
    private List<Map<String,String>> careers;

    @NotNull
    private int price;

    @NotBlank
    private String description;

    @NotEmpty
    private List<Map<String,String>> snsAddrs;

}
