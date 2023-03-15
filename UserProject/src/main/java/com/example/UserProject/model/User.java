package com.example.UserProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="userTable")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotBlank(message="User name must not be blank")
    @Size(min=2,message = "Size should be minimum of 2 chars")
    String userName;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Enter email in valid format")
    String email;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "Date cannot be null")
    @Past(message = "Enter valid date")
    Date dateOfBirth;
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    String phoneNumber;
}
