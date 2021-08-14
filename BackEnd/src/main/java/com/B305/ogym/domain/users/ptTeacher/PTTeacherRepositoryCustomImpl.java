package com.B305.ogym.domain.users.ptTeacher;


import static com.B305.ogym.domain.mappingTable.QPTStudentPTTeacher.pTStudentPTTeacher;
import static com.B305.ogym.domain.users.ptStudent.QMonthly.monthly;
import static com.B305.ogym.domain.users.ptStudent.QPTStudent.pTStudent;
import static com.B305.ogym.domain.users.ptTeacher.QCareer.career;
import static com.B305.ogym.domain.users.ptTeacher.QCertificate.certificate;
import static com.B305.ogym.domain.users.ptTeacher.QPTTeacher.pTTeacher;
import static com.B305.ogym.domain.users.ptTeacher.QSns.sns;

import com.B305.ogym.controller.dto.HealthDto.MyStudentsHealthListResponse;
import com.B305.ogym.controller.dto.HealthDto.StudentHealth;
import com.B305.ogym.controller.dto.PTDto.SearchDto;
import com.B305.ogym.controller.dto.UserDto;
import com.B305.ogym.controller.dto.UserDto.CareerDto;
import com.B305.ogym.controller.dto.UserDto.CertificateDto;
import com.B305.ogym.controller.dto.UserDto.SnsDto;
import com.B305.ogym.domain.mappingTable.PTStudentPTTeacher;
import com.B305.ogym.domain.users.common.Gender;
import com.B305.ogym.domain.users.ptStudent.Monthly;
import com.B305.ogym.domain.users.ptStudent.PTStudent;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class PTTeacherRepositoryCustomImpl implements PTTeacherRepositoryCustom {

    private final EntityManager em;

    private final JPAQueryFactory queryFactory;
    Map<String, Expression> check = new HashMap<>();

    public PTTeacherRepositoryCustomImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);

        check.put("id", pTTeacher.id);
        check.put("email", pTTeacher.email);
        check.put("username", pTTeacher.username);
        check.put("nickname", pTTeacher.nickname);
        check.put("age", pTTeacher.age);
        check.put("gender", pTTeacher.gender);
        check.put("tel", pTTeacher.tel);
        check.put("address", pTTeacher.address);
        check.put("role", pTTeacher.authority);
        check.put("major", pTTeacher.major);
        check.put("price", pTTeacher.price);
        check.put("description", pTTeacher.description);
    }

    @Override
    public MyStudentsHealthListResponse findMyStudentsHealth(String teacherEmail) {
        List<PTStudent> students = queryFactory.select(pTStudent)
            .from(pTStudentPTTeacher)
            .join(pTStudentPTTeacher.ptStudent, pTStudent)
            .join(pTStudentPTTeacher.ptTeacher, pTTeacher)
            .where(pTTeacher.email.eq(teacherEmail))
            .fetch();

        List<StudentHealth> result = new ArrayList<>();
        students.stream().forEach(o->{
            StudentHealth studentHealth = StudentHealth.builder()
                .username(o.getUsername())
                .nickname(o.getNickname())
                .age(o.getAge())
                .gender(o.getGender())
//                .profileUrl(o.getProfilePicture().getPictureAddr())
                .build();
            List<Monthly> monthly = o.getMonthly();
            monthly.sort((o1,o2)->{
                return o1.getMonth() - o2.getMonth();
            });
            monthly.stream().forEach( m -> {
                studentHealth.addWeight(m.getWeight());
                studentHealth.addHeight(m.getHeight());
            });
            result.add(studentHealth);
        });

        MyStudentsHealthListResponse myStudentsHealthListResponse = new MyStudentsHealthListResponse();
        myStudentsHealthListResponse.setStudentHealthList(result);
        return myStudentsHealthListResponse;
    }

    @Override
    public Map<String, Object> getInfo(String teacherEmail, List<String> req) { // "username" , "id"

        Tuple result = queryFactory
            .select(pTTeacher.id, pTTeacher.email, pTTeacher.username, pTTeacher.nickname, pTTeacher.age,
                pTTeacher.gender, pTTeacher.tel, pTTeacher.address, pTTeacher.authority,
                pTTeacher.major, pTTeacher.price, pTTeacher.description)
            .from(pTTeacher)
            .where(pTTeacher.email.eq(teacherEmail))
            .fetchOne(); // pTTeahcer의 정보
        Map<String, Object> map = new HashMap<>();

        req.forEach(o -> {
            if ("certificates".equals(o)) {
                List<CertificateDto> certificates = queryFactory
                    .select(Projections.fields(CertificateDto.class,
                        certificate.name.as("name"),
                        certificate.publisher.as("publisher"),
                        certificate.date.as("date")))
                    .from(certificate)
                    .where(certificate.ptTeacher.email.eq(teacherEmail))
                    .fetch();
                map.put(o, certificates);
            } else if ("careers".equals(o)) {
                List<CareerDto> careers = queryFactory
                    .select(Projections.fields(CareerDto.class,
                        career.company.as("company"),
                        career.startDate.as("startDate"),
                        career.endDate.as("endDate"),
                        career.role.as("role")))
                    .from(career)
                    .where(career.ptTeacher.email.eq(teacherEmail))
                    .fetch();
                map.put(o, careers);
            } else if ("snss".equals(o)) {
                List<SnsDto> snss = queryFactory
                    .select(Projections.fields(SnsDto.class,
                        sns.url.as("url"),
                        sns.platform.as("platform")))
                    .from(sns)
                    .where(sns.ptTeacher.email.eq(teacherEmail))
                    .fetch();
                map.put(o, snss);
            }else {
                assert result != null;
                map.put(o, result.get(check.get(o)));
            }
        });

        return map;
    }

    // 선생님 리스트 조건 검색
    @Override
    public Page<PTTeacher> searchAll(SearchDto searchDto, Pageable pageable) {

        QueryResults<PTTeacher> results = queryFactory
            .selectFrom(pTTeacher)
            .where(
                likeName(searchDto.getName()),
                eqGender(searchDto.getGender()),
                loeMaxPrice(searchDto.getMaxPrice()),
                goeMinPrice(searchDto.getMinPrice()),
                loeMaxAge(searchDto.getMaxAge()),
                goeMinAge(searchDto.getMinAge())
            )
            .offset(pageable.getOffset())   // 페이징 처리(offset)
            .limit(pageable.getPageSize())  // 페이징 처리(한 페이지에 출력할 개체 수)
            .orderBy(pTTeacher.starRating.desc())   // 별점 높은 순으로 출력
            .fetchResults();    // 결과 + count
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    // 이름 포함
    private BooleanExpression likeName(String username) {
        if (username == null) {
            return null;
        }
        return pTTeacher.username.like("%" + username + "%");
    }

    // 성별 일치
    private BooleanExpression eqGender(Gender gender) {
        if (gender == null) {
            return null;
        }
        return pTTeacher.gender.eq(gender);
    }

    // 최소 가격
    private BooleanExpression loeMaxPrice(Integer maxPrice) {
        if (maxPrice == null) {
            return null;
        }
        return pTTeacher.price.loe(maxPrice);
    }

    // 최대 가격
    private BooleanExpression goeMinPrice(Integer minPrice) {
        if (minPrice == null) {
            return null;
        }
        return pTTeacher.price.goe(minPrice);
    }


    // 최대 나이
    private BooleanExpression loeMaxAge(Integer maxAge) {
        if (maxAge == null) {
            return null;
        }
        return pTTeacher.age.loe(maxAge);
    }

    // 최소 나이
    private BooleanExpression goeMinAge(Integer minAge) {
        if (minAge == null) {
            return null;
        }
        return pTTeacher.age.goe(minAge);

    }

    @Override
    public List<LocalDateTime> reservationTime(String teacherEmail) {
        return em.createQuery("select st.reservationDate "
            + " from PTStudentPTTeacher st "
            + " join st.ptTeacher t "
            + " where t.email =: teacherEmail", LocalDateTime.class)
            .setParameter("teacherEmail", teacherEmail)
            .getResultList();
    }



    @Override
    public List<PTStudentPTTeacher> getReservationTime(String teacherEmail){
        return em.createQuery("select pt"
            + " from PTStudentPTTeacher pt"
            + " join fetch pt.ptTeacher t"
            + " join fetch pt.ptStudent s"
            + " where t.email =: teacherEmail"
            + " order by pt.reservationDate",PTStudentPTTeacher.class)
            .setParameter("teacherEmail",teacherEmail)
            .getResultList();
    }
}


