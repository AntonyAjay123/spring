package com.example.doctorBooking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;
    private Integer age;
    private String name;
    private String illness;

    private Timestamp admitDate;
    @JoinColumn(name="doctor_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;
}
