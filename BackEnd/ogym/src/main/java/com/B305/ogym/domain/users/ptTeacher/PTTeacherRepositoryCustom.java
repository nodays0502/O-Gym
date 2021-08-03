package com.B305.ogym.domain.users.ptTeacher;

import com.B305.ogym.controller.dto.HealthDto.MyStudentsHealthListResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface PTTeacherRepositoryCustom {
    MyStudentsHealthListResponse findMyStudentsHealth(Long teacherEmail);
}
