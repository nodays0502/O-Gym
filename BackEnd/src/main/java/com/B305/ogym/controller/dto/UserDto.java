package com.B305.ogym.controller.dto;

import com.B305.ogym.domain.users.common.Address;
import com.B305.ogym.domain.users.common.Gender;
import com.B305.ogym.domain.users.ptStudent.PTStudent;
import com.B305.ogym.domain.users.ptTeacher.Career;
import com.B305.ogym.domain.users.ptTeacher.Certificate;
import com.B305.ogym.domain.users.ptTeacher.PTTeacher;
<<<<<<< HEAD
=======
import com.B305.ogym.domain.users.ptTeacher.Sns;
>>>>>>> 091e6aa5c83db24a5d5b183e28fef92ad935d842
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDto {

    @Getter
<<<<<<< HEAD
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SaveTeacherRequest {
=======
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SaveUserRequest {
>>>>>>> 091e6aa5c83db24a5d5b183e28fef92ad935d842

        @NotEmpty
        @Size(min = 3, max = 50, message = "이메일 똑바로 입력하세요.")
        @Pattern(regexp = "\\S+@\\S+(.com)$")
        private String email;

        //    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @NotEmpty
        @Size(min = 3, max = 20, message = "비밀번호는 3자이상 20자 이하로 입력해주세요.")
        private String password;

        @NotEmpty
        @Size(min = 2, max = 10, message = "올바르게 이름을 입력하세요.")
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
        @Pattern(regexp = "\\d{5}", message = "올바른 우편번호를 입력하세요.")
        private String zipCode; // 우편번호

        @NotEmpty(message = "도로명 주소를 입력하세요.")
        private String street; // 도로명 주소

        @NotEmpty(message = "상세주소를 입력하세요.")
        private String detailedAddress; // 상세 주소 ?

        @NotEmpty(message = "권한 주세요.")
        private String role;

<<<<<<< HEAD
        @NotBlank
        private String major;

//        @NotEmpty
        private List<Certificate> certificates;

//        @NotEmpty
        public List<Career> careers;

        @NotNull
        private int price;

        @NotBlank
        private String description;

//        @NotEmpty
        private List<SnsDto> snsAddrs;

        @Getter
        @NoArgsConstructor(access = AccessLevel.PUBLIC)
        static class SnsDto {

            private String snsUrl;
            private String snsName;
        }

        public PTTeacher toEntity() {
=======
        // --------------------------
//        @NotEmpty
        private List<Integer> monthlyHeights;

        //        @NotEmpty
        private List<Integer> monthlyWeights;

        public PTStudent toPTStudentEntity() {
>>>>>>> 091e6aa5c83db24a5d5b183e28fef92ad935d842

            Gender gender = Gender.MAN;
            if (this.gender == 1) {
                gender = Gender.WOMAN;
            }

            Address address = Address.createAddress(
                zipCode,
                street,
                detailedAddress
            );

<<<<<<< HEAD
            return PTTeacher.builder()
=======
            return PTStudent.builder()
>>>>>>> 091e6aa5c83db24a5d5b183e28fef92ad935d842
                .email(email)
                .username(username)
                .nickname(nickname)
                .gender(gender)
                .tel(tel)
                .address(address)
<<<<<<< HEAD
                .description(description)
                .price(price)
                .major(major)
                .certificates(new ArrayList<>())
                .careers(new ArrayList<>())
                .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SaveStudentRequest {

        @NotEmpty
        @Size(min = 3, max = 50, message = "이메일 똑바로 입력하세요.")
        @Pattern(regexp = "\\S+@\\S+(.com)$")
        private String email;

        //    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @NotEmpty
        @Size(min = 3, max = 20, message = "비밀번호는 3자이상 20자 이하로 입력해주세요.")
        private String password;

        @NotEmpty
        @Size(min = 2, max = 10, message = "올바르게 이름을 입력하세요.")
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
        @Pattern(regexp = "\\d{5}", message = "올바른 우편번호를 입력하세요.")
        private String zipCode; // 우편번호

        @NotEmpty(message = "도로명 주소를 입력하세요.")
        private String street; // 도로명 주소

        @NotEmpty(message = "상세주소를 입력하세요.")
        private String detailedAddress; // 상세 주소 ?

        @NotEmpty(message = "권한 주세요.")
        private String role;

        @NotEmpty
        private List<Integer> monthlyHeights;

        @NotEmpty
        private List<Integer> monthlyWeights;

        public PTStudent toEntity() {
=======
                .build();
        }
        // ----------

        //        @NotBlank
        private String major;

        //        @NotEmpty
        private List<Certificate> certificates;

        //        @NotEmpty
        public List<Career> careers;

        //        @NotNull
        private int price;

        //        @NotBlank
        private String description;

        //        @NotEmpty
        private List<Sns> snsAddrs;

        public PTTeacher toPTTeacherEntity() {
>>>>>>> 091e6aa5c83db24a5d5b183e28fef92ad935d842

            Gender gender = Gender.MAN;
            if (this.gender == 1) {
                gender = Gender.WOMAN;
            }

            Address address = Address.createAddress(
                zipCode,
                street,
                detailedAddress
            );

<<<<<<< HEAD
            return PTStudent.builder()
=======
            return PTTeacher.builder()
>>>>>>> 091e6aa5c83db24a5d5b183e28fef92ad935d842
                .email(email)
                .username(username)
                .nickname(nickname)
                .gender(gender)
                .tel(tel)
                .address(address)
<<<<<<< HEAD
                .build();
        }

    }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SaveUserRequest {

        @NotEmpty
        @Size(min = 3, max = 50, message = "이메일 똑바로 입력하세요.")
        @Pattern(regexp = "\\S+@\\S+(.com)$")
        private String email;

        //    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @NotEmpty
        @Size(min = 3, max = 20, message = "비밀번호는 3자이상 20자 이하로 입력해주세요.")
        private String password;

        @NotEmpty
        @Size(min = 2, max = 10, message = "올바르게 이름을 입력하세요.")
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
        @Pattern(regexp = "\\d{5}", message = "올바른 우편번호를 입력하세요.")
        private String zipCode; // 우편번호

        @NotEmpty(message = "도로명 주소를 입력하세요.")
        private String street; // 도로명 주소

        @NotEmpty(message = "상세주소를 입력하세요.")
        private String detailedAddress; // 상세 주소 ?

        @NotEmpty(message = "권한 주세요.")
        private String role;
=======
                .description(description)
                .price(price)
                .major(major)
                .certificates(new ArrayList<>())
                .careers(new ArrayList<>())
                .build();
        }
>>>>>>> 091e6aa5c83db24a5d5b183e28fef92ad935d842
    }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class GetUserInfoRequest {

        @NotEmpty
        private List<String> req;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
<<<<<<< HEAD
    public static class CareerDto{
=======
    public static class CareerDto {

>>>>>>> 091e6aa5c83db24a5d5b183e28fef92ad935d842
        private String role;

        private LocalDate startDate;
        private LocalDate endDate;

        private String company;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
<<<<<<< HEAD
    public static class CertificateDto{
=======
    public static class CertificateDto {
>>>>>>> 091e6aa5c83db24a5d5b183e28fef92ad935d842

        private String name; // 자격증 명칭

        private String publisher; // 발급 기관

        private LocalDate date; // 획득일
    }

}
