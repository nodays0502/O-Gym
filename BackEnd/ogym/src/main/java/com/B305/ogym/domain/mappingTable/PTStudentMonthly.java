package com.B305.ogym.domain.mappingTable;

import com.B305.ogym.domain.users.ptStudent.Monthly;
import com.B305.ogym.domain.users.ptStudent.PTStudent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "pt_student_monthly")
public class PTStudentMonthly {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pt_student_monthly_id")
    private Long id; // 대리 키

    private int height; // 키
    private int weight; // 몸무게
    private int year; // 년도

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private PTStudent ptStudent; // 학생

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "month")
    private Monthly monthly; // 월
}
