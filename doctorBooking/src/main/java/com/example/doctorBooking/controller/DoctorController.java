package com.example.doctorBooking.controller;

import com.example.doctorBooking.model.Doctor;
import com.example.doctorBooking.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @GetMapping("/")
    public List<Doctor> findAll(){
        return doctorService.findAll();
    }

    @PostMapping("/addDoc")
    public ResponseEntity<List<String>> saveDoctor(@RequestBody String doc){
         //return doctorService.saveDoctor(doc);
        List<String> validDoc = validate(doc);
        log.info("validDoc="+validDoc);
        if(validDoc.isEmpty()) {
            Doctor newDoc = setDoctor(new JSONObject(doc));
            doctorService.saveDoctor(newDoc);
            validDoc.add("doctor saved");
            return new ResponseEntity<>(validDoc, HttpStatus.CREATED);
        }
        else return new ResponseEntity<>(validDoc,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/doctor")
    public Doctor getById(@RequestParam int id){
        return doctorService.getById(id);
    }

    public List<String> validate(String doc) {
        List<String> errors = new ArrayList<>();
        JSONObject json = new JSONObject(doc);
        if (!json.has("doctorName")) {
            errors.add("Enter doctor name");
        }
        if (!json.has("experience")) {
            errors.add("Enter doctor experience");
        }
        if (!json.has("specializedIn")) {
            errors.add("enter doctor specialization");
        }
        return errors;
    }

    public Doctor setDoctor(JSONObject json){
        Doctor newDoctor = new Doctor();
        newDoctor.setDoctorName(json.getString("doctorName"));
        newDoctor.setExperience(json.getString("experience"));
        newDoctor.setSpecializedIn(json.getString("specializedIn"));
        return newDoctor;
    }
}
