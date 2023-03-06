package com.example.doctorBooking.dao;

import com.example.doctorBooking.model.Doctor;
import com.example.doctorBooking.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {
    List<Patient> findByDoctor(Doctor doctor);
}
