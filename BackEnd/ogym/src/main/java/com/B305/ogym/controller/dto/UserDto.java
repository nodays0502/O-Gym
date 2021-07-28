package com.B305.ogym.controller.dto;

import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignupRequest{

        @NotEmpty
        @Size(min = 3, max = 50, message = "이메일 똑바로 입력하세요.")
        @Pattern(regexp = "\\S+@\\S+(.com)$")
        private String email;

        //    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @NotEmpty
        @Size(min = 3, max = 20, message = "비밀번호는 3자이상 20자 이하로 입력해주세요.")
        private String password;

        @NotEmpty
        @Size(min = 2 , max = 10, message = "올바르게 이름을 입력하세요.")
        private String username;

        @NotEmpty
        @Size(min = 3, max = 50, message = "닉네임은 2자 이상 10자 이하로 입력해주세요.")
        private String nickname;

        @NotNull(message = "성별 입력하세요.")
        private Integer gender; // 0 MAN , 1 WOMAN

        @NotEmpty(message = "올바른 휴대폰 번호를 입력하세요.")
        @Pattern(regexp = "\\d{3}-\\d{3,4}-\\d{4}")
        private String tel;

        @NotEmpty
        @Pattern(regexp = "\\d{5}" ,message = "올바른 우편번호를 입력하세요.")
        private String zipCode; // 우편번호

        @NotEmpty(message = "도로명 주소를 입력하세요.")
        private String street; // 도로명 주소

        @NotEmpty(message = "상세주소를 입력하세요.")
        private String detailedAddress; // 상세 주소 ?

        @NotEmpty(message = "권한 주세요.")
        private String role;
    }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateStudentRequest{
        @NotEmpty
        List<Integer> monthlyHeights;

        @NotEmpty
        List<Integer> monthlyWeights;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateTeacherRequest{
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


}
