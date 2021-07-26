package com.B305.ogym.domain.users.ptTeacher;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sns {
    @Column(name = "sns_url")
    private String url; // sns 주소

    @Column(name = "platform_url")
    private String platform; // 어떤 플랫폼

//    private String snsId; // sns 아이디
    @Builder
    public Sns(String url, String platform){
        this.url = url;
        this.platform = platform;
    }
}
