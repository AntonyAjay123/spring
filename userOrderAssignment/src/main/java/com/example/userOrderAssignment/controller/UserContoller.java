package com.example.userOrderAssignment.controller;

import com.example.userOrderAssignment.model.User;
import com.example.userOrderAssignment.service.UserService;
import jakarta.annotation.Nullable;
import org.apache.coyote.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserContoller {
    @Autowired
    UserService userService;
    @PostMapping("/users")
    public ResponseEntity saveUser(@RequestBody User user){
        userService.saveUser(user);
        return new ResponseEntity("User saved", HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity getUser(@Nullable @RequestParam Integer id){
        if(id==null){
            JSONArray users = userService.findAll();
            return new ResponseEntity(users.toList(),HttpStatus.ACCEPTED);
        }
        else{
            JSONObject user = userService.findUser(id);
            return new ResponseEntity(user.toMap(),HttpStatus.ACCEPTED);
        }
    }
}
