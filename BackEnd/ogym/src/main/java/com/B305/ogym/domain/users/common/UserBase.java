package com.B305.ogym.domain.users.common;

import com.B305.ogym.domain.autority.Authority;
import com.B305.ogym.domain.users.BaseTimeEntity;
import java.util.Set;
import javax.persistence.CascadeType;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user_base")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @ManyToOne
    @JoinColumn(name = "authority")
    private Authority authority;

    @Builder
    public UserBase(String email , String password , String nickname, Authority authority){
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.authority = authority;
    }
}
