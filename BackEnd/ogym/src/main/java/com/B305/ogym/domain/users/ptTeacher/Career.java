package com.B305.ogym.domain.users.ptTeacher;

import com.B305.ogym.domain.users.BaseTimeEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="carrer_id")
    private String id;

    // 시작일, 종료일
    private LocalDateTime startDate;
    private LocalDateTime endTime;

}
