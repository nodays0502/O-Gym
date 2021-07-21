package com.B305.ogym.domain.users.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    @Builder
    public Address(String zipCode, String street, String detailedAddress) {
        this.zipCode = zipCode;
        this.street = street;
        this.detailedAddress = detailedAddress;
    }

    private String zipCode; // 우편번호

    private String street; // 도로명 주소

    private String detailedAddress; // 상세 주소

}
