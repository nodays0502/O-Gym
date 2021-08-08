package com.B305.ogym.service;

import com.B305.ogym.controller.dto.PTDto;
import com.B305.ogym.controller.dto.PTDto.CancelReservationRequest;
import com.B305.ogym.domain.mappingTable.PTStudentPTTeacher;
import com.B305.ogym.domain.mappingTable.PTStudentPTTeacherRepository;
import com.B305.ogym.domain.users.ptStudent.PTStudent;
import com.B305.ogym.domain.users.ptStudent.PTStudentRepository;
import com.B305.ogym.domain.users.ptTeacher.PTTeacher;
import com.B305.ogym.domain.users.ptTeacher.PTTeacherRepository;
import com.B305.ogym.exception.user.UserNotFoundException;
import com.sun.jdi.request.DuplicateRequestException;
import java.time.LocalDateTime;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PTService {

    private final PTTeacherRepository ptTeacherRepository;
    private final PTStudentRepository ptStudentRepository;
    private final PTStudentPTTeacherRepository ptStudentPTTeacherRepository;

    // 예약 생성
    @Transactional
    public void makeReservation(String studentEmail, PTDto.SaveReservationRequest request){
        String teacherEmail = request.getPtTeacherEmail();
        LocalDateTime time = request.getReservationTime();

        PTTeacher ptTeacher = ptTeacherRepository.findByEmail(teacherEmail);
        if(ptTeacher==null) throw new UserNotFoundException("teacher");
        PTStudent ptStudent = ptStudentRepository.findByEmail(studentEmail);

        try {
            PTStudentPTTeacher ptStudentPTTeacher = ptStudent
                .makeReservation(ptTeacher, ptStudent, time);
            ptStudentPTTeacherRepository.save(ptStudentPTTeacher);
        }catch(RuntimeException ex){
            throw new DuplicateRequestException("중복된 예약");
        }
    }

    @Transactional
    public void cancleReservation(CancelReservationRequest request) {
        ptStudentPTTeacherRepository.deleteById(Long.valueOf(request.getReservationId()));
    }
}
