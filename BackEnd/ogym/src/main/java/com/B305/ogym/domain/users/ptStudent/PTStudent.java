package com.B305.ogym.domain.users.ptStudent;

import com.B305.ogym.domain.mappingTable.PTStudentMonthly;
import com.B305.ogym.domain.mappingTable.PTStudentPTTeacher;
import com.B305.ogym.domain.users.common.UserBase;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pt_student")
@PrimaryKeyJoinColumn(name = "pt_student_id")
public class PTStudent extends UserBase {

    @OneToMany(mappedBy = "ptStudent")
    private List<PTStudentMonthly> ptStudentMonthly = new ArrayList<>(); // 월 별 체중 , 키

    @OneToMany(mappedBy = "ptStudent")
    private List<PTStudentPTTeacher> ptStudentPTTeachers = new ArrayList<>(); // 예약 정보
}
