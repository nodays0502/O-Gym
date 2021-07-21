package com.B305.ogym.domain.users.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {

    private String zipCode; // 우편번호

    private String street; // 도로명 주소

    private String detailedAddress; // 상세 주소
}
