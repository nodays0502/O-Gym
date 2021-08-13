package com.B305.ogym.service;

import static org.junit.jupiter.api.Assertions.*;

import com.B305.ogym.domain.users.UserRepository;
import com.B305.ogym.domain.users.ptStudent.MonthlyRepository;
import com.B305.ogym.domain.users.ptStudent.PTStudentRepository;
import com.B305.ogym.domain.users.ptTeacher.PTTeacherRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HealthServiceTest {


    @Mock
    PTTeacherRepository ptTeacherRepository;
    @Mock
    PTStudentRepository ptStudentRepository;
    @Mock
    MonthlyRepository monthlyRepository;
    @Mock
    UserRepository userRepository;

    @InjectMocks
    HealthService healthService;


}