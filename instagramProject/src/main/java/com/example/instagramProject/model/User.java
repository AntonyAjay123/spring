package com.example.instagramProject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column( columnDefinition = "varchar(255) default 'user_instagram'")
    private String userName;
    private Integer age;
    private String email;
    private String phoneNumber;
}
