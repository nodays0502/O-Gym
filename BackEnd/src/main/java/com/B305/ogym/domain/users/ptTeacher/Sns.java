package com.B305.ogym.domain.users.ptTeacher;

import javax.persistence.Column;
import javax.persistence.Entity;
<<<<<<< HEAD
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
=======
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
>>>>>>> 091e6aa5c83db24a5d5b183e28fef92ad935d842
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sns {

    @Id
<<<<<<< HEAD
    @GeneratedValue
    @Column(name = "sns_id")
    private Long id;

    private String platform; // 어떤 플랫폼

}
=======
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sns_id")
    private Long id;

    private String url;

    private String platform; // 어떤 플랫폼

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pt_teacher_id")
    private PTTeacher ptTeacher;

    public void setPtTeacher(PTTeacher ptTeacher){
        this.ptTeacher = ptTeacher;
    }

    @Builder
    public Sns(String url, String platform, PTTeacher ptTeacher){
        this.url = url;
        this.platform = platform;
        this.ptTeacher = ptTeacher;
    }
}

>>>>>>> 091e6aa5c83db24a5d5b183e28fef92ad935d842
