package com.B305.ogym.domain.users.ptStudent;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "monthly")
public class Monthly {

    public Monthly(int month) {
        this.month = month;
    }

    @Id
    private int month; // 달
}
