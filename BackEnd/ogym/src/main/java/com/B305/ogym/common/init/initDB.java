package com.B305.ogym.common.init;

import com.B305.ogym.domain.mappingTable.PTStudentMonthly;
import com.B305.ogym.domain.mappingTable.PTStudentPTTeacher;
import com.B305.ogym.domain.users.common.Address;
import com.B305.ogym.domain.users.common.Gender;
import com.B305.ogym.domain.users.ptStudent.PTStudent;
import com.B305.ogym.domain.users.ptTeacher.Certificate;
import com.B305.ogym.domain.users.ptTeacher.PTTeacher;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class initDB {

    private final InitService initService;

    @Autowired  // 단일 생성자이기 때문에 Annotation 붙이지 않아도 상관없음
    public initDB(InitService initService) {
        this.initService = initService;
    }

    @PostConstruct
    public void init() {
        // initService 메서드 수행
        initService.putPTStudent();
        initService.putPTTeacher();

        // issue: certificate 엔티티 구조에 대해서 논의 필요
        // 각 자격증마다 가진 사람 리스트 저장 vs 각 PT 트레이너가 가진 자격증 저장
//        initService.putCertificate();
    }

    @Service
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em; // @RequiredArgsConstructor 어노테이션을 선언함으로서

        // final로 선언된 객체에 대한 생성자를 spring이 잡아서 의존성 주입을 해줌.
        // EMF 선언해서 EM을 따로 뽑아낼 필요가 없다!
        public void putPTStudent() {

            Address address = Address.builder()
                .zipCode("34153")
                .street("대전광역시 유성구 동서대로 98-39")
                .detailedAddress("싸피 사무국")
                .build();

            List<PTStudentPTTeacher> teachers = new ArrayList<>();
            List<PTStudentMonthly> monthly = new ArrayList<>();

            PTStudent ptStudent = PTStudent.builder()
                .password("ssafy")
                .nickname("김지우")
                .address(address)
                .email("chuu@ssafy.com")
                .tel("010-2021-0721")
                .gender(Gender.WOMAN)
                .ptStudentMonthly(monthly)
                .ptStudentPTTeachers(teachers)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

            em.persist(ptStudent);

        }

        public void putPTTeacher() {
            Address address = Address.builder()
                .zipCode("06220")
                .street("서울시 강남구 테헤란로 212")
                .detailedAddress("싸피 사무국")
                .build();

            Certificate certificate1 = Certificate.builder()
                .name("NSCA-kor")
                .build();
            Certificate certificate2 = Certificate.builder()
                .name("NASM-CPT")
                .build();
            Certificate certificate3 = Certificate.builder()
                .name("NASM-PES")
                .build();
            Certificate certificate4 = Certificate.builder()
                .name("NASM-CES")
                .build();

            Set<PTStudentPTTeacher> students = new LinkedHashSet<>();
            List<Certificate> certificates = new ArrayList<>();

            certificates.add(certificate1);
            certificates.add(certificate2);
            certificates.add(certificate3);
            certificates.add(certificate4);

            PTTeacher ptTeacher = PTTeacher.builder()
                .password("ssafy")
                .nickname("김계란")
                .address(address)
                .email("eggkim@ssafy.com")
                .tel("010-2021-0105")
                .ptStudentPTTeachers(students)
                .certificates(certificates)
                .major("특공무술")
                .gender(Gender.MAN)
                .description("거짓은 머리털만큼도 없다! 신뢰와 정직으로 모시겠습니다")
                .snsAddr("https://www.instagram.com/physical_gallery_egg/?hl=ko")
                .price(500000)
                .starRating(4)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

            em.persist(ptTeacher);

        }
//        public void putCertificate(){
//            Certificate certificate = Certificate.builder()
//                .name("NSCA")
//                .build();
//            em.persist(certificate);
//        }

    }
}
