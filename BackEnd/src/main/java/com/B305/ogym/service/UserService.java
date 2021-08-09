package com.B305.ogym.service;

import com.B305.ogym.common.util.SecurityUtil;
import com.B305.ogym.controller.dto.UserDto;
<<<<<<< HEAD
import com.B305.ogym.controller.dto.UserDto.SaveStudentRequest;
import com.B305.ogym.controller.dto.UserDto.SaveTeacherRequest;
=======
>>>>>>> 091e6aa5c83db24a5d5b183e28fef92ad935d842
import com.B305.ogym.domain.authority.Authority;
import com.B305.ogym.domain.authority.AuthorityRepository;
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
<<<<<<< HEAD
import com.B305.ogym.exception.user.UserDuplicateException;
=======
import com.B305.ogym.exception.user.NotValidRequestParamException;
import com.B305.ogym.exception.user.UserDuplicateEmailException;
import com.B305.ogym.exception.user.UserDuplicateException;
import com.B305.ogym.exception.user.UserDuplicateNicknameException;
>>>>>>> 091e6aa5c83db24a5d5b183e28fef92ad935d842
import java.util.List;
import java.util.Map;
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
    //    private final PTStudentMonthlyRepository ptStudentMonthlyRepository;
    private final PTTeacherRepository ptTeacherRepository;
    private final PTStudentRepository ptStudentRepository;
    private final AuthorityRepository authorityRepository;


    @Transactional
<<<<<<< HEAD
    public void signup(SaveStudentRequest StudentRequest) {
        if (userRepository.findOneWithAuthoritiesByEmail(StudentRequest.getEmail())
            != null) {
            throw new UserDuplicateException("이미 가입되어 있는 유저입니다.");
        }

        Authority studentRole = authorityRepository.findById("ROLE_PTSTUDENT").get();
        PTStudent ptStudent = StudentRequest.toEntity();
        ptStudent.setPassword(passwordEncoder.encode(StudentRequest.getPassword()));
        ptStudent.setRole(studentRole);
        ptStudentRepository.save(ptStudent);

        List<Monthly> months = monthlyRepository.findAll(); // 1 ~ 12
//        for (int i = 0; i < 12; i++) { // 12개월 다 넣는다.
////            Optional<Monthly> month = monthlyRepository.findById(i + 1); //  미리 리스트로 받아서 사용하자
////            Monthly monthly = month.orElse(new Monthly(i + 1));
//            PTStudentMonthly ptStudentMonthly = PTStudentMonthly.createHealth(
//                StudentRequest.getMonthlyHeights().get(i),
//                StudentRequest.getMonthlyHeights().get(i),
//                ptStudent, //  연관관계 편의 메소드
//                months.get(i)
//            );
//            ptStudentMonthlyRepository.save(ptStudentMonthly);
//        }

    }

    @Transactional
    public void signup(SaveTeacherRequest teacherRequest) {
        if (userRepository.findOneWithAuthoritiesByEmail(teacherRequest.getEmail())
            != null) {
            throw new UserDuplicateException("이미 가입되어 있는 유저입니다.");
        }

        Authority teacherRole = authorityRepository.findById("ROLE_PTTEACHER").get();

        PTTeacher ptTeacher = teacherRequest.toEntity();
        ptTeacher.setPassword(passwordEncoder.encode(teacherRequest.getPassword()));
        ptTeacher.setRole(teacherRole);

        teacherRequest.getCertificates().stream().forEach(o -> ptTeacher.addCertificate(o));
        teacherRequest.getCareers().stream().forEach(o -> ptTeacher.addCareer(o));
        ptTeacherRepository.save(ptTeacher);
    }


    public UserBase getUserWithAuthority(String email) {
        return userRepository.findOneWithAuthoritiesByEmail(email);
    }

    public UserBase getMyUserWithAuthorities() {
        Optional<String> result = SecurityUtil.getCurrentUsername();
        if (result.isEmpty()) {
            return null;
        } else {
            return userRepository.findOneWithAuthoritiesByEmail(result.get());
        }

    }

    @Transactional
    public void deleteUserBase() {
        UserBase userBase = getMyUserWithAuthorities();
        userRepository.delete(userBase);
    }

    @Transactional
    public Map<String, Object> getUserInfo(List<String> req) {
        UserBase userBase = getMyUserWithAuthorities();
        if (userBase.getAuthority().getAuthorityName().equals("ROLE_PTTEACHER")) {
            return ptTeacherRepository.getInfo(userBase.getId(), req);
        } else {
            return null;
        }
    }


=======
    public void signup(UserDto.SaveUserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new UserDuplicateEmailException("이미 가입되어 있는 email입니다.");
        }
        if (userRepository.existsByNickname(userRequest.getNickname())) {
            throw new UserDuplicateNicknameException("이미 가입되어 있는 nickname입니다.");
        }
        if ("ROLE_PTTEACHER".equals(userRequest.getRole())) {
            Authority teacherRole = authorityRepository.findById("ROLE_PTTEACHER").get();

            PTTeacher ptTeacher = userRequest.toPTTeacherEntity();
            ptTeacher.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            ptTeacher.setRole(teacherRole);

            userRequest.getCertificates().forEach(ptTeacher::addCertificate);
            userRequest.getCareers().forEach(ptTeacher::addCareer);
            userRequest.getSnsAddrs().forEach(ptTeacher::addSns);
            ptTeacherRepository.save(ptTeacher);
        } else {
            Authority studentRole = authorityRepository.findById("ROLE_PTSTUDENT").get();
            PTStudent ptStudent = userRequest.toPTStudentEntity();
            ptStudent.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            ptStudent.setRole(studentRole);
            if (userRequest.getMonthlyHeights().size() != 12
                || userRequest.getMonthlyWeights().size() != 12) {
                throw new NotValidRequestParamException("12개월의 health 정보를 입력하지 않았습니다.");
            }
            for (int i = 0; i < 12; i++) {
                ptStudent.addMonthly(i + 1, userRequest.getMonthlyHeights().get(i),
                    userRequest.getMonthlyWeights().get(i));
            }
            ptStudentRepository.save(ptStudent);
        }
    }

//    public UserBase getMyUserWithAuthorities() {
//        Optional<String> result = SecurityUtil.getCurrentUsername();
//        if (result.isEmpty()) {
//            return null;
//        } else {
//            return userRepository.findOneWithAuthoritiesByEmail(result.get());
//        }
//
//    }

    @Transactional
    public void deleteUserBase(Long userId) {
        userRepository.deleteById(userId); // 이렇게 해도 되나?
    }

    @Transactional
    public Map<String, Object> getUserInfo(UserBase user, List<String> req) {
        if ("ROLE_PTTEACHER".equals(user.getRole())) {
            return ptTeacherRepository.getInfo(user.getId(), req);
        } else {
            return ptStudentRepository.getInfo(user.getId(), req);
        }
    }

>>>>>>> 091e6aa5c83db24a5d5b183e28fef92ad935d842
}
