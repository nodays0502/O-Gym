package com.B305.ogym.domain.users.ptStudent;

import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
public interface PTStudentRepository extends JpaRepository<PTStudent,Long> {
=======
public interface PTStudentRepository extends JpaRepository<PTStudent,Long>,PTStudentRepositoryCustom {
>>>>>>> 091e6aa5c83db24a5d5b183e28fef92ad935d842
    PTStudent findByEmail(String email);

}
