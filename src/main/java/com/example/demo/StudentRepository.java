package com.example.demo;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

//    @Query(" SELECT s FROM student WHERE s.email=?1 ")
    Optional<Student> findStudentByEmail(String email);

    @Transactional
    @Modifying
    @Query("DELETE FROM student u WHERE u.id = ?1")
    int deleteStudentById(Long id);
}
