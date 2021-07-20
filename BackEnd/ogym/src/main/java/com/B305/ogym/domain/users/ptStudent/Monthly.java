package com.B305.ogym.domain.users.ptStudent;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name="monthly")
public class Monthly {

    @Id
    private int month;
}
