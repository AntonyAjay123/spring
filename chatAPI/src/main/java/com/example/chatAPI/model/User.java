package com.example.chatAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(unique = true)
    private String userName;
    private String password;
    private String gender;
    private String phoneNumber;
    private Integer age;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private Timestamp createdDate;
    @UpdateTimestamp
    private Timestamp updatedDate;
    private String email;

    @JoinColumn(name = "status_id")
    @ManyToOne
    private Status status;

}
