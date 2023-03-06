package com.example.doctorBooking.controller;

import com.example.doctorBooking.dao.DoctorRepository;
import com.example.doctorBooking.model.Doctor;
import com.example.doctorBooking.model.Patient;
import com.example.doctorBooking.service.PatientService;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class PatientController {
    @Autowired
    PatientService patientService;
    @Autowired
    DoctorRepository doctorRepository;

    @PostMapping("/patients")
    public ResponseEntity<List<String>> savePatient(@RequestBody String patient){
        JSONObject json = new JSONObject(patient);
        List<String> errors = validPatient(json);
        if(errors.isEmpty()){
            errors.add("Patient saved");
            Patient newPatient = setPatient(json);
            patientService.savePatient(newPatient);
            return new ResponseEntity<>(errors, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }
    }

    public List<String> validPatient(JSONObject json){
        List<String> errors = new ArrayList<>();
        if(!json.has("name"))
            errors.add("Enter patient name");
        if(!json.has("illness"))
            errors.add("Enter patient illness");
        if(!json.has("doctorId")) {
            errors.add("Enter doctor id");
        }
        else{
            try {
                Doctor doctor = doctorRepository.findById(json.getInt("doctorId")).get();
            }
            catch (Exception e){
                errors.add(String.valueOf(e));
            }
        }
        if(!json.has("age"))
            errors.add("enter patient age");
        return errors;
    }
    public Patient setPatient(JSONObject json){
        Patient newPatient = new Patient();
        newPatient.setName(json.getString("name"));
        newPatient.setAge(json.getInt("age"));
        newPatient.setIllness(json.getString("illness"));
        Doctor doctor = doctorRepository.findById(json.getInt("doctorId")).get();
        newPatient.setDoctor(doctor);
        Long curTime = System.currentTimeMillis();
        newPatient.setAdmitDate(new Timestamp(curTime));
        return newPatient;
    }

    @GetMapping("/patients")
    public ResponseEntity findAll(@Nullable @RequestParam Integer patientId,
                                 @Nullable @RequestParam Integer doctorId){
        log.info("patientId"+patientId);
        if (doctorId==null) {
            if (patientId == null){
                JSONArray array = patientService.findAll();
                return new ResponseEntity(array.toString(),HttpStatus.OK);
            }
            else return new ResponseEntity(patientService.findById(patientId),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(patientService.findByDoctorId(doctorId),HttpStatus.OK);
        }
    }
}
