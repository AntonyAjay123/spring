package com.example.UserProject.controller;

import com.example.UserProject.model.User;
import com.example.UserProject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/findById")
    public User findById(@RequestParam int id){
        return userService.findById(id);
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity<>(errors.toString(),HttpStatus.BAD_REQUEST);
        }
        userService.addUser(user);
        return new ResponseEntity<>("Successfully added", HttpStatus.CREATED);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity<>(errors.toString(),HttpStatus.BAD_REQUEST);
        }
        userService.updateUser(user);
        return new ResponseEntity<>("Successfully updated",HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestParam int id){
        userService.deleteUser(id);
        return new ResponseEntity<>("Successfully deleted",HttpStatus.OK);
    }
}
