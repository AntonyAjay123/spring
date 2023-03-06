package com.example.doctorBooking.service;

import com.example.doctorBooking.dao.DoctorRepository;
import com.example.doctorBooking.dao.PatientRepository;
import com.example.doctorBooking.model.Patient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    public JSONArray findAll(){
        List<Patient> patients = patientRepository.findAll();
        JSONArray array = new JSONArray();
        for(Patient patient:patients){
            JSONObject json = new JSONObject();
            json.put("name",patient.getName());
            json.put("age",patient.getAge());
            json.put("illness",patient.getIllness());
            json.put("admitDate",patient.getAdmitDate());
            json.put("doctorId",patient.getDoctor().getDoctorId());
            array.put(json);
        }
        return array;
    }

    public void savePatient(Patient newPatient){
        patientRepository.save(newPatient);
    }
    public List<Patient> findById(Integer id){
        List<Patient> patient= new ArrayList<>();
        patient.add(patientRepository.findById(id).get());
        return patient;
    }

    public List<Patient> findByDoctorId(Integer id){
        return patientRepository.findByDoctor(doctorRepository.findById(id).get());
    }

//    [{
//        "name":"kid-2",
//                "illness":"fever",
//                "age":10,
//                "doctorId":2
//    },
//    {
//        "name":"kid-3",
//            "illness":"fever-3",
//            "age":11,
//            "doctorId":2
//    },{
//        "name":"adult-1",
//                "illness":"heart",
//                "age":44,
//                "doctorId":1
//    }
//]
}
