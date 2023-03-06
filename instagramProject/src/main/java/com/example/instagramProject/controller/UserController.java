package com.example.instagramProject.controller;

import com.example.instagramProject.Util.UserUtil;
import com.example.instagramProject.model.User;
import com.example.instagramProject.service.UserService;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserUtil userUtil;

    @PostMapping("/user")
    public ResponseEntity saveUser(@RequestBody String user){
        JSONObject json = new JSONObject(user);
        List<String> errors = userService.validUser(json);
        log.info(""+errors.isEmpty());
        if(errors.isEmpty()) {
            User newUser = userUtil.setUser(new JSONObject(user),null);
            return new ResponseEntity(userService.saveUser(newUser), HttpStatus.CREATED);
        }
        else{
            try
            {
                JSONObject errorList = new JSONObject();
                errorList.put("errorMessage", "Missing parameters");
                errorList.put("missing parameters", errors);
                return new ResponseEntity<>(errorList.toString(), HttpStatus.BAD_REQUEST);
            }
            catch(JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @GetMapping("/user")
    public ResponseEntity getUser(@Nullable @RequestParam Integer id){
        if(id==null)
            return new ResponseEntity(userService.findAll(),HttpStatus.OK);
        else return userService.findById(id);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity updateUser(@PathVariable int userId,@RequestBody String user){
        JSONObject json=new JSONObject(user);
        User updatedUser =  userUtil.setUser(json,userId);
        if(updatedUser==null)
            return new ResponseEntity("No such user found",HttpStatus.BAD_REQUEST);
        else return new ResponseEntity("Updated successfully", HttpStatus.OK);
    }

}
