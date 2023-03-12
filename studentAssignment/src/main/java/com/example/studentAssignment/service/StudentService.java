package com.example.studentAssignment.service;

import com.example.studentAssignment.dao.StudentRepository;
import com.example.studentAssignment.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public void saveStudent(Student student){
        studentRepository.save(student);
    }

    public List<Student> findActiveUsers(boolean active){
        if(active==true)
            return studentRepository.findActiveUsers();
        else return studentRepository.findInactiveUsers();
    }

    public List<Student> findFirstName(String firstName){
        return studentRepository.findFirstName(firstName);
    }

    public List<Student> findLastName(String lastName){
        return studentRepository.findLastName(lastName);
    }

    public List<Student> findAge(int age){
        return studentRepository.findAge(age);
    }
}
