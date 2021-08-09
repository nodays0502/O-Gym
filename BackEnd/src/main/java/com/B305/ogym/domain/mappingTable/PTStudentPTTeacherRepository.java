package com.B305.ogym.domain.mappingTable;

import com.B305.ogym.domain.users.ptStudent.PTStudent;
import com.B305.ogym.domain.users.ptTeacher.PTTeacher;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PTStudentPTTeacherRepository extends JpaRepository<PTStudentPTTeacher, Long> {
    PTStudentPTTeacher findByPtTeacherAndPtStudentAndReservationDate(PTTeacher ptTeacher, PTStudent ptStudent,LocalDateTime reservationTime);
}
