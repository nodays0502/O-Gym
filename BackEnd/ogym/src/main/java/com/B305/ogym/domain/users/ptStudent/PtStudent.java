package com.B305.ogym.domain.users.ptStudent;

import com.B305.ogym.domain.mappingTable.PTStudentMonthly;
import com.B305.ogym.domain.users.common.UserBase;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PtStudent extends UserBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "ptStudent")
    private List<PTStudentMonthly> ptStudentMonthly = new ArrayList<>();

}
