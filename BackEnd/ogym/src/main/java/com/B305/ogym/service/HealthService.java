package com.B305.ogym.service;

import com.B305.ogym.controller.dto.HealthDto;
import com.B305.ogym.domain.users.ptTeacher.PTTeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HealthService {
    private final PTTeacherRepository ptTeacherRepository;

    public HealthDto.MyStudentsHealthListResponse findMyStudentsHealth(Long teacherId){
        return ptTeacherRepository.findMyStudentsHealth(teacherId);
    }
}
