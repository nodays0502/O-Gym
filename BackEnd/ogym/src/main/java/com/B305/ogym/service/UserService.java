package com.B305.ogym.service;

import com.B305.ogym.exception.user.UserDuplicateException;
import com.B305.ogym.common.util.SecurityUtil;
import com.B305.ogym.controller.dto.UserDto;
import com.B305.ogym.domain.autority.Authority;
import com.B305.ogym.domain.mappingTable.PTStudentMonthly;
import com.B305.ogym.domain.mappingTable.PTStudentMonthlyRepository;
import com.B305.ogym.domain.users.UserRepository;
import com.B305.ogym.domain.users.common.Address;
import com.B305.ogym.domain.users.common.Gender;
import com.B305.ogym.domain.users.common.UserBase;
import com.B305.ogym.domain.users.ptStudent.Monthly;
import com.B305.ogym.domain.users.ptStudent.MonthlyRepository;
import com.B305.ogym.domain.users.ptStudent.PTStudent;
import com.B305.ogym.domain.users.ptStudent.PTStudentRepository;
import com.B305.ogym.domain.users.ptTeacher.PTTeacher;
import com.B305.ogym.domain.users.ptTeacher.PTTeacherRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MonthlyRepository monthlyRepository;
    private final PTStudentMonthlyRepository ptStudentMonthlyRepository;
    private final PTTeacherRepository ptTeacherRepository;
    private final PTStudentRepository ptStudentRepository;


    @Transactional
    public void signup(UserDto.SignupRequest signupReqeust) {
        if (userRepository.findOneWithAuthoritiesByEmail(signupReqeust.getEmail())
            != null) {
            throw new UserDuplicateException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
            .authorityName(signupReqeust.getRole())
            .build();

        Gender gender = Gender.MAN;
        if (signupReqeust.getGender() == 1) {
            gender = Gender.WOMAN;
        }

        Address address = Address.builder()
            .zipCode(signupReqeust.getZipCode())
            .street(signupReqeust.getStreet())
            .detailedAddress(signupReqeust.getDetailedAddress())
            .build();

        if ("ROLE_PTTEACHER".equals (signupReqeust.getRole ())) {
            PTTeacher ptTeacher = PTTeacher.builder()
                .email(signupReqeust.getEmail())
                .password(passwordEncoder.encode(signupReqeust.getPassword()))
                .username(signupReqeust.getUsername ())
                .nickname(signupReqeust.getNickname())
                .gender(gender)
                .tel(signupReqeust.getTel())
                .address(address)
                .authority(authority)
                .build();

            ptTeacherRepository.save(ptTeacher);

        }else if("ROLE_PTSTUDENT".equals(signupReqeust.getRole()) ){
            PTStudent ptStudent = PTStudent.builder()
                .email(signupReqeust.getEmail())
                .password(passwordEncoder.encode(signupReqeust.getPassword()))
                .username(signupReqeust.getUsername ())
                .nickname(signupReqeust.getNickname())
                .gender(gender)
                .tel(signupReqeust.getTel())
                .address(address)
                .authority(authority)
                .build();

            ptStudentRepository.save(ptStudent);
        }else{
            System.out.println ("??????? 머함");
        }

    }

    public UserBase getUserWithAuthorities(String email) {
        return userRepository.findOneWithAuthoritiesByEmail(email);
    }

    public UserBase getMyUserWithAuthorities() {
        Optional<String> result = SecurityUtil.getCurrentUsername();
        if (result.isEmpty()) {
            return null;
        } else {
            return  userRepository.findOneWithAuthoritiesByEmail(result.get());
        }

    }

    @Transactional
    public void deleteUserBase() {
        UserBase userBase = getMyUserWithAuthorities();
        userRepository.delete(userBase);
    }
//    @Transactional
//    public void changeStudent(UpdateStudentRequestDto updateStudentRequestDto) {
//        UserBase userBase = getMyUserWithAuthorities();
//        PTStudent ptStudent = (PTStudent) userBase;
//        for (int i = 0; i < 12; i++) { // 12개월 다 넣는다.
//            Optional<Monthly> month = monthlyRepository.findById(i + 1);
//            Monthly monthly = month.orElse(new Monthly(i+1));
//            PTStudentMonthly ptStudentMonthly = PTStudentMonthly.builder()
//                .monthly(monthly)
//                .height(updateStudentRequestDto.getMonthlyHeights().get(i))
//                .weight(updateStudentRequestDto.getMonthlyWeights().get(i))
//                .ptStudent(ptStudent)
//                .build();
//
//        }
//
//    }

    @Transactional
    public void changeStudent(UserDto.UpdateStudentRequest updateStudentRequestDto) {
        UserBase userBase = getMyUserWithAuthorities();
        PTStudent ptStudent = (PTStudent) userBase;
        for (int i = 0; i < 12; i++) { // 12개월 다 넣는다.
            Optional<Monthly> month = monthlyRepository.findById(i + 1);
            Monthly monthly = month.orElse(new Monthly(i+1));
            PTStudentMonthly ptStudentMonthly = PTStudentMonthly.builder()
                .monthly(monthly)
                .height(updateStudentRequestDto.getMonthlyHeights().get(i))
                .weight(updateStudentRequestDto.getMonthlyWeights().get(i))
                .ptStudent(ptStudent)
                .build();
            ptStudentMonthlyRepository.save(ptStudentMonthly);
        }
    }
    public void changeTeacher() {

    }
}
