package com.B305.ogym.domain.users.ptTeacher;

import com.B305.ogym.domain.users.common.UserBase;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name="pt_teacher")
public class PTTeacher extends UserBase {
    // 평점
    private Integer starRating;

    // 전문분야
    private String major;

    // 자격증 리스트
    @OneToMany(mappedBy = "id")
    private List<Certificate> certificates = new ArrayList<>();

    // 가격
    private Integer price;

    // 자기소개
    private String description;

    // SNS 링크
    private String snsAddr;

    // 경력 리스트
    @OneToMany
    @JoinColumn(name="career_id")
    private List<Career> careers = new ArrayList<>();

    // 학생 리스트
//    @OneToMany(mappedBy = "")
//    private Set<PtStudent> students = new LinkedHashSet<>();

    // 이력?
}
