package com.B305.ogym.service;

import com.B305.ogym.controller.dto.PTDto.AllTeacherListResponse;
import com.B305.ogym.controller.dto.PTDto.CareerDto;
import com.B305.ogym.controller.dto.PTDto.CertificateDto;
import com.B305.ogym.controller.dto.PTDto.PTTeacherDto;
import com.B305.ogym.controller.dto.PTDto.SnsDto;
import com.B305.ogym.controller.dto.PTDto.reservationRequest;
import com.B305.ogym.domain.mappingTable.PTStudentPTTeacher;
import com.B305.ogym.domain.mappingTable.PTStudentPTTeacherRepository;
import com.B305.ogym.domain.users.common.UserBase;
import com.B305.ogym.domain.users.ptStudent.PTStudent;
import com.B305.ogym.domain.users.ptStudent.PTStudentRepository;
import com.B305.ogym.domain.users.ptTeacher.Career;
import com.B305.ogym.domain.users.ptTeacher.Certificate;
import com.B305.ogym.domain.users.ptTeacher.PTTeacher;
import com.B305.ogym.domain.users.ptTeacher.PTTeacherRepository;
import com.B305.ogym.domain.users.ptTeacher.Sns;
import com.B305.ogym.exception.user.UnauthorizedException;
import com.B305.ogym.exception.user.UserNotFoundException;
import com.sun.jdi.request.DuplicateRequestException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PTService {

    private final PTTeacherRepository ptTeacherRepository;
    private final PTStudentRepository ptStudentRepository;
    private final PTStudentPTTeacherRepository ptStudentPTTeacherRepository;

    // 예약 생성
    @Transactional
    public void makeReservation(UserBase user, reservationRequest request) {
        if (!user.getRole().equals("ROLE_PTSTUDENT")) {
            throw new UnauthorizedException("선생님은 예약불가능");
        }

        String studentEmail = user.getEmail();
        String teacherEmail = request.getPtTeacherEmail();
        LocalDateTime time = request.getReservationTime();

        PTTeacher ptTeacher = ptTeacherRepository.findByEmail(teacherEmail)
            .orElseThrow(() -> new UserNotFoundException("TEACHER"));

        PTStudent ptStudent = ptStudentRepository.findByEmail(studentEmail)
            .orElseThrow(() -> new UserNotFoundException("해당하는 이메일은 존재하지 않습니다."));

        try {
            PTStudentPTTeacher ptStudentPTTeacher = ptStudent
                .makeReservation(ptTeacher, ptStudent, time);
            ptStudentPTTeacherRepository.save(ptStudentPTTeacher);
        } catch (RuntimeException ex) {
            throw new DuplicateRequestException("중복된 예약");
        }
    }

    @Transactional
    public void cancleReservation(String ptStudentEmail, reservationRequest request) {

        // 로그인한 사용자 찾을 수 없음
        PTStudent ptStudent = ptStudentRepository.findByEmail(ptStudentEmail)
            .orElseThrow(() -> new UnauthorizedException("로그인한 사용자 없음"));

        // 선생님 정보 찾을 수 없음
        PTTeacher ptTeacher = ptTeacherRepository.findByEmail(request.getPtTeacherEmail())
            .orElseThrow(() -> new UserNotFoundException("CANCLE_RESERVATION"));

        // 예약정보 찾을 수 없음
        PTStudentPTTeacher ptStudentPTTeacher = ptStudentPTTeacherRepository
            .findByPtTeacherAndPtStudentAndReservationDate(ptTeacher, ptStudent,
                request.getReservationTime())
            .orElseThrow(() -> new UserNotFoundException("CANCLE_RESERVATION"));

        ptStudentPTTeacherRepository.delete(ptStudentPTTeacher);

    }

    @Transactional
    public AllTeacherListResponse getTeacherList() {

        List<PTTeacher> ptTeachers = ptTeacherRepository.findAll();

//        for(int i = 0; i < ptTeachers.size(); i++){
//            List<Certificate> certificates = ptTeachers.get(i).getCertificates();
//            for(int j = 0; j < certificates.size(); j++){
//                certificates.get(j).setPtTeacher(null);
//            }
//        }

        List<PTTeacherDto> ptTeacherDtos = new ArrayList<>();
        for (int i = 0; i < ptTeachers.size(); i++) {
            PTTeacher ptTeacher = ptTeachers.get(i);

            List<Certificate> certificates = ptTeacher.getCertificates();
            List<CertificateDto> certificateDtos = new ArrayList<>();

            for (int j = 0; j < certificates.size(); j++) {
                Certificate certificate = certificates.get(j);
                CertificateDto certificateDto = CertificateDto.builder()
                    .name(certificate.getName())
                    .date(certificate.getDate())
                    .publisher(certificate.getPublisher())
                    .build();
                certificateDtos.add(certificateDto);
            }

            List<Career> careers = ptTeacher.getCareers();
            List<CareerDto> careerDtos = new ArrayList<>();

            for (int j = 0; j < careers.size(); j++) {
                Career career = careers.get(j);
                CareerDto careerDto = CareerDto.builder()
                    .company(career.getCompany())
                    .startDate(career.getStartDate())
                    .endDate(career.getEndDate())
                    .role(career.getRole())
                    .build();
                careerDtos.add(careerDto);
            }

            Set<PTStudentPTTeacher> ptStudentPTTeachers = ptTeacher.getPtStudentPTTeachers();
            List<PTStudentPTTeacher> ptStudentPTTeachersList = new ArrayList<>(ptStudentPTTeachers);
            List<LocalDateTime> reservations = new ArrayList<>();


            for (int j = 0; j < ptStudentPTTeachersList.size(); j++) {
                PTStudentPTTeacher ptStudentPTTeacher = ptStudentPTTeachersList.get(j);
                reservations.add(ptStudentPTTeacher.getReservationDate());
            }

            Collections.sort(reservations);

            List<Sns> snsList = ptTeacher.getSnss();
            List<SnsDto> snsDtos = new ArrayList<>();

            for (int j = 0; j < snsList.size(); j++) {
                Sns sns = snsList.get(j);
                SnsDto snsDto = SnsDto.builder()
                    .platform(sns.getPlatform())
                    .url(sns.getUrl())
                    .build();
                snsDtos.add(snsDto);
            }

            PTTeacherDto ptTeacherDto = PTTeacherDto.builder().username(ptTeacher.getUsername())
                .gender(ptTeacher.getGender())
                .nickname(ptTeacher.getNickname())
                .tel(ptTeacher.getTel())
                .email(ptTeacher.getEmail())
                .profilePicture(ptTeacher.getProfilePicture())
                .starRating(ptTeacher.getStarRating())
                .major(ptTeacher.getMajor())
                .price(ptTeacher.getPrice())
                .description(ptTeacher.getDescription())
                .certificates(certificateDtos)
                .careers(careerDtos)
                .reservations(reservations)
                .snsList(snsDtos)
                .build();

            ptTeacherDtos.add(ptTeacherDto);
        }

        AllTeacherListResponse allTeacherListResponse = AllTeacherListResponse.builder()
            .teacherList(ptTeacherDtos)
            .build();

        return allTeacherListResponse;
    }
}
