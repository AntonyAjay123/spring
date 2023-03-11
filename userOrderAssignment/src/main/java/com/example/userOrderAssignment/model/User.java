package com.example.userOrderAssignment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int userId;
    String name;
    String email;
    String password;
    String phoneNumber;
}
