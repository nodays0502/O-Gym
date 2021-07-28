package com.B305.ogym.controller.dto;

import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class HealthDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UserInfoRequestListDto {
        @NotEmpty
        private List<String> req;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UserInfoResponseListDto {
        private List<Map<String,Object>> info;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class StudentHealthResponseDto {
        private List<Integer> heightList;
        private List<Integer> weightList;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MyPTStudentHealthListResponseDto {
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
}
