package com.B305.ogym.domain.users.common;

import com.B305.ogym.domain.users.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user_base")
@DiscriminatorColumn(name = "DTYPE")
public class UserBase extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id; // 대리 키

    private String password; // 비밀번호

    @Embedded
    private Address address; // 주소

    private String nickname; // 닉네임

    private String tel; // 연락처

    @Enumerated
    private Gender gender; // 성별

    @Column(unique = true)
    private String email; // 이메일

    @Enumerated
    private ProfilePicture profilePicture; // 프로필 사진
}
