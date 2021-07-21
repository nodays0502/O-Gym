package com.B305.ogym.domain.users.ptTeacher;

import com.B305.ogym.domain.mappingTable.PTStudentPTTeacher;
import com.B305.ogym.domain.users.common.UserBase;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pt_teacher")
@PrimaryKeyJoinColumn(name = "pt_teacher_id")
public class PTTeacher extends UserBase {

    // 평점
    private int starRating;

    // 전문분야
    private String major;

    // 자격증 리스트
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Certificate> certificates = new ArrayList<>();

    // 가격
    private int price;

    // 자기소개
    private String description;

    // SNS 링크
    private String snsAddr;

    // 경력 리스트
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "career_id")
    private List<Career> careers = new ArrayList<>();

    // 학생 리스트
    @OneToMany(mappedBy = "ptTeacher")
    private Set<PTStudentPTTeacher> ptStudentPTTeachers = new LinkedHashSet<>();

    // 이력?
}
