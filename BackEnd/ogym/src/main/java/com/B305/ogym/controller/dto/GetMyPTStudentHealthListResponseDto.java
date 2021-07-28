package com.B305.ogym.controller.dto;

import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class GetMyPTStudentHealthListResponseDto {
    private List<StudentHealth> studentHealthList;
    static class StudentHealth{
        private String username;
        private String nickname;
        private int age ;
        private int gender ;
        private String profileUrl;
        private List<Integer> heightList;
        private List<Integer> weightList;
    }
}
