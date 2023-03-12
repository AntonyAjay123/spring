package com.example.studentAssignment.dao;

import com.example.studentAssignment.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query(value = "select * from tbl_student where active=true",nativeQuery = true)
    List<Student> findActiveUsers();
    @Query(value = "select * from tbl_student where active = false",nativeQuery = true)
    List<Student> findInactiveUsers();
    @Query(value = "select * from tbl_student where first_name=:firstName",nativeQuery = true)
    List<Student> findFirstName(String firstName);

    @Query(value="select * from tbl_student where last_name=:lastName",nativeQuery = true)
    List<Student> findLastName(String lastName);
    @Query(value = "select * from tbl_student where age=:age",nativeQuery = true)
    List<Student> findAge(int age);
}
