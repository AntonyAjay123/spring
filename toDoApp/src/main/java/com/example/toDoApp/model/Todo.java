package com.example.toDoApp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // used to create database table
public class Todo {
    @Id // used to create primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// used to implement auto-increment function
   // @Column(name="todo_id")
    int id;
  //  @Column(name="todo_title")
    String title;
    boolean status;


}
