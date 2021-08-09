package com.B305.ogym.service;

import com.B305.ogym.controller.dto.HealthDto;
import com.B305.ogym.controller.dto.HealthDto.GetMyHealthResponse;
<<<<<<< HEAD
=======
import com.B305.ogym.domain.users.common.UserBase;
>>>>>>> 091e6aa5c83db24a5d5b183e28fef92ad935d842
import com.B305.ogym.domain.users.ptStudent.PTStudent;
import com.B305.ogym.domain.users.ptStudent.PTStudentRepository;
import com.B305.ogym.domain.users.ptTeacher.PTTeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HealthService {

    private final PTTeacherRepository ptTeacherRepository;
    private final PTStudentRepository ptStudentRepository;


    // 로그인한 사용자의 건강정보 조회
    @Transactional
<<<<<<< HEAD
    public HealthDto.GetMyHealthResponse getMyHealthResponse() {

        ////
        // Token으로부터 claim 걸어서 사용자 정보 가져와야함 (이메일)
        ////

        String email = "chuu@ssafy.com";
=======
    public HealthDto.GetMyHealthResponse getMyHealthResponse(String email) {

        System.out.println("email: " +  email);

>>>>>>> 091e6aa5c83db24a5d5b183e28fef92ad935d842
        PTStudent ptStudent = ptStudentRepository.findByEmail(email);

        GetMyHealthResponse myHealthResponse = ptStudent.getMyHealthResponse(ptStudent);

        return myHealthResponse;
    }

<<<<<<< HEAD
    private final UserService userService;

    public HealthDto.MyStudentsHealthListResponse findMyStudentsHealth() {
        String teacherEmail = userService.getMyUserWithAuthorities().getEmail();
        return ptTeacherRepository.findMyStudentsHealth(teacherEmail);
=======

    public HealthDto.MyStudentsHealthListResponse findMyStudentsHealth(Long teacherId) {
        return ptTeacherRepository.findMyStudentsHealth(teacherId);
>>>>>>> 091e6aa5c83db24a5d5b183e28fef92ad935d842
    }
}
