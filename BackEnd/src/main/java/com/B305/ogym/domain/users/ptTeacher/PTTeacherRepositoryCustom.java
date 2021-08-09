package com.B305.ogym.domain.users.ptTeacher;

import com.B305.ogym.controller.dto.HealthDto.MyStudentsHealthListResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Repository;

@Repository
public interface PTTeacherRepositoryCustom {
<<<<<<< HEAD
    MyStudentsHealthListResponse findMyStudentsHealth(String teacherEmail);
=======
    MyStudentsHealthListResponse findMyStudentsHealth(Long teacherId);
>>>>>>> 091e6aa5c83db24a5d5b183e28fef92ad935d842
    Map<String,Object> getInfo(Long teacherId, List<String> req);
}
