package com.B305.ogym.domain.users.ptTeacher;



import com.B305.ogym.controller.dto.HealthDto;

import com.B305.ogym.controller.dto.HealthDto.MyStudentsHealthListResponse;
import com.B305.ogym.controller.dto.HealthDto.StudentHealth;
import com.B305.ogym.domain.users.ptStudent.QMonthly;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import static com.B305.ogym.domain.mappingTable.QPTStudentPTTeacher.pTStudentPTTeacher;
import static com.B305.ogym.domain.users.ptTeacher.QPTTeacher.pTTeacher;
import static com.B305.ogym.domain.users.ptStudent.QPTStudent.pTStudent;
import static com.B305.ogym.domain.users.ptStudent.QMonthly.monthly;

public class PTTeacherRepositoryCustomImpl implements PTTeacherRepositoryCustom{

    private final EntityManager em;

    private final JPAQueryFactory queryFactory;

    public PTTeacherRepositoryCustomImpl(EntityManager em){
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public MyStudentsHealthListResponse findMyStudentsHealth(Long teacherId) {
        System.out.println(teacherId);
        List<StudentHealth> result = queryFactory
            .select(Projections.fields(StudentHealth.class,
                pTStudent.username.as("username"),
                pTStudent.nickname.as("nickname"),
//                pTStudent.age,
                pTStudent.gender.as("gender"),
                pTStudent.profilePicture.pictureAddr.as("profileUrl")

            ))
            .from(pTStudentPTTeacher)
            .join(pTStudentPTTeacher.ptTeacher, pTTeacher)
            .join(pTStudentPTTeacher.ptStudent, pTStudent)
//            .join(pTStudentPTTeacher.ptStudent)
            .where(pTStudentPTTeacher.ptTeacher.id.eq(teacherId))
            .fetch();

        List<String> students = result.stream().map(o -> o.getUsername()).collect(Collectors.toList());
        System.out.println(students.size());

        List<Tuple> studentshealth = queryFactory
            .select(monthly.height, monthly.weight,monthly.ptStudent.username)
            .from(monthly)
            .join(monthly.ptStudent, pTStudent)
            .where(monthly.ptStudent.username.in(students))
            .orderBy(monthly.month.asc())
            .fetch();

        result.stream().forEach( o -> {
            studentshealth.stream().forEach( t -> {
                if(t.get(monthly.ptStudent.username).equals(o.getUsername())){
                    o.addHeight(t.get(monthly.height));
                    o.addWeight(t.get(monthly.weight));
                }
            });
        });
        System.out.println(result.size());;
        MyStudentsHealthListResponse myStudentsHealthListResponse = new MyStudentsHealthListResponse();
        myStudentsHealthListResponse.setStudentHealthList(result);
        return myStudentsHealthListResponse;
    }
}
