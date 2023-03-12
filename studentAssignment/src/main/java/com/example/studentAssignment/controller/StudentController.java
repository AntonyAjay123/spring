package com.example.studentAssignment.controller;

import com.example.studentAssignment.model.Student;
import com.example.studentAssignment.service.StudentService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public ResponseEntity saveStudent(@RequestBody Student student){
        studentService.saveStudent(student);
        return new ResponseEntity("Student saved", HttpStatus.CREATED);
    }

    @GetMapping("/student")
    public ResponseEntity getStudents(@Nullable @RequestParam Boolean active,
                                      @Nullable @RequestParam String firstName,
                                      @Nullable @RequestParam String lastName,
                                      @Nullable @RequestParam Integer age){
        List<Student> students=new ArrayList<>();
        if(active!=null)
            students= studentService.findActiveUsers(active);
        if(firstName!=null)
            students=studentService.findFirstName(firstName);
        if(lastName!=null)
            students=studentService.findLastName(lastName);
        if(age!=null)
            students=studentService.findAge(age);
        return new ResponseEntity(students,HttpStatus.OK);
    }
}
