package com.B305.ogym.domain.users.ptStudent;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Monthly {

    @Id
    private int month;
}
