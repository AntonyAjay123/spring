package com.example.doctorBooking.service;

import com.example.doctorBooking.dao.DoctorRepository;
import com.example.doctorBooking.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    public Doctor saveDoctor(Doctor newDoctor){
        String name = newDoctor.getDoctorName();
        name = "Dr."+name;
        newDoctor.setDoctorName(name);
        return doctorRepository.save(newDoctor);
    }

    public List<Doctor> findAll(){
        return  doctorRepository.findAll();
    }

    public Doctor getById(int id){
        return doctorRepository.findById(id).get();
    }
}
