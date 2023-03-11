package com.example.userOrderAssignment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int addressId;

    String phoneNumber;
    String zipcode;
    String state;
    @JoinColumn(name="user_id")
    @OneToOne
    User userId;
}
