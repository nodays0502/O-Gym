package com.B305.ogym.domain.users.ptTeacher;

import com.B305.ogym.domain.users.common.UserBase;
import com.B305.ogym.domain.users.ptStudent.PtStudent;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.Getter;

@Entity
@Getter
public class PTTeacher extends UserBase {
    // 평점
    private Integer starRating;

    // 전문분야
    private String major;

    // 경력 리스트
    @OneToMany(mappedBy = "id")
    private List<Certificate> certificates = new ArrayList<>();

    // 가격
    private Integer price;

    // 자기소개
    private String description;

    // SNS 링크
    private String snsAddr;

    // 학생 리스트
    @OneToMany(mappedBy = "")
    private Set<PtStudent> students = new LinkedHashSet<>();

    // 이력?
}
