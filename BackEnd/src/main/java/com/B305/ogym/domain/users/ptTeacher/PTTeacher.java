package com.B305.ogym.domain.users.ptTeacher;

import com.B305.ogym.domain.mappingTable.PTStudentPTTeacher;
<<<<<<< HEAD
import com.B305.ogym.domain.mappingTable.PTTeacherSns;
=======
>>>>>>> 091e6aa5c83db24a5d5b183e28fef92ad935d842
import com.B305.ogym.domain.users.common.UserBase;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("Teacher")
@Table(name = "pt_teacher")
@PrimaryKeyJoinColumn(name = "pt_teacher_id")
public class PTTeacher extends UserBase {

    // 평점
    private float starRating;

    // 전문분야
    private String major;

    // 자격증 리스트
    @Builder.Default
<<<<<<< HEAD
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
=======
    @OneToMany(mappedBy = "ptTeacher", cascade = CascadeType.ALL)
>>>>>>> 091e6aa5c83db24a5d5b183e28fef92ad935d842
    private List<Certificate> certificates = new ArrayList<>();

    // 가격
    private int price;

    // 자기소개
    private String description;

    // SNS 링크
    @Builder.Default
    @OneToMany(mappedBy = "ptTeacher", cascade = CascadeType.ALL )
<<<<<<< HEAD
    private List<PTTeacherSns> ptTeacherSns = new ArrayList<>();
=======
    private List<Sns> snss = new ArrayList<>();
>>>>>>> 091e6aa5c83db24a5d5b183e28fef92ad935d842

    // 경력 리스트
    @Builder.Default
    @OneToMany(mappedBy = "ptTeacher",cascade = CascadeType.ALL)
    private List<Career> careers = new ArrayList<>();

    // 학생 리스트
    @Builder.Default
    @OneToMany(mappedBy = "ptTeacher", cascade = CascadeType.ALL)
    private Set<PTStudentPTTeacher> ptStudentPTTeachers = new LinkedHashSet<>();

    // 이력?

    public void addCertificate(Certificate certificate){
//        if(this.certificates == null){
//            this.certificates = new ArrayList<>();
//        }
        this.certificates.add(certificate);
        if(certificate.getPtTeacher() != this){
            certificate.setPtTeacher(this);
        }
    }

    public void addCareer(Career career){
//        if(this.careers == null) {
//            this.careers = new ArrayList<>();
//        }
        this.careers.add(career);
        if(career.getPtTeacher() != this){
            career.setPtTeacher(this);
        }
    }
<<<<<<< HEAD
=======
    public void addSns(Sns sns){
        this.snss.add(sns);
        if(sns.getPtTeacher() != this){
            sns.setPtTeacher(this);
        }
    }
>>>>>>> 091e6aa5c83db24a5d5b183e28fef92ad935d842

}
