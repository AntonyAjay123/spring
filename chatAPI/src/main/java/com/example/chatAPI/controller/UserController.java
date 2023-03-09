package com.example.chatAPI.controller;

import com.example.chatAPI.Util.UserUtil;
import com.example.chatAPI.model.User;
import com.example.chatAPI.service.UserService;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserUtil userUtil;

    @PostMapping("/")
    public ResponseEntity saveUser(@RequestBody String user) {
        JSONObject json = new JSONObject(user);
        List<String> errors = userUtil.validUser(json);
        if (errors.isEmpty()) {
            User newUser = userUtil.setUser(json, null);
            int userId = userService.saveUser(newUser);
            return new ResponseEntity<>("User saved with id " + userId, HttpStatus.CREATED);
        } else {
            try {
                JSONObject errorList = new JSONObject();
                errorList.put("error Message", "errors in input");
                errorList.put("errors", errors);
                return new ResponseEntity(errorList.toMap(), HttpStatus.BAD_REQUEST);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @GetMapping("/users")
    public ResponseEntity getUsers(@Nullable @RequestParam String active){
        JSONArray users;
        if(active==null){
            users=userService.findAll();
            return new ResponseEntity<>(users.toList(),HttpStatus.OK);
        }
        if(active.equalsIgnoreCase("true")){
            users = userService.activeUsers();
            return new ResponseEntity(users.toList(),HttpStatus.OK);
        }
        else {
            users = userService.inactiveUsers();
            return new ResponseEntity(users.toList(),HttpStatus.OK);
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity deleteUser(@RequestParam int userId){
        log.info("delete started");
        if(userUtil.checkUser(userId)){
            userService.deleteUser(userId);
            return new ResponseEntity<>("User deleted",HttpStatus.ACCEPTED);
        }
        else return new ResponseEntity("Enter valid userId",HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/users")
    public ResponseEntity updateUser(@RequestParam int userId,@RequestBody String user){
        if(userUtil.checkUser(userId)){
            JSONObject json = new JSONObject(user);
            List<String> errors = userUtil.validUpdate(json,userId);
            if(errors.isEmpty()){
                User updatedUser = userUtil.setUser(json,userId);
                userService.saveUser(updatedUser);
                return new ResponseEntity<>("User updated",HttpStatus.OK);
            }
            else return new ResponseEntity(errors,HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity<>("Enter valid userId",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody String loginCred){
        JSONObject json = new JSONObject(loginCred);
        JSONObject errors = new JSONObject();
        String userName="",password="";
        if(!json.has("userName"))
            errors.put("userName","missing parameter");
        else
            userName= json.getString("userName");
        if(!json.has("password"))
            errors.put("password","missing parameter");
        else
            password= json.getString("password");
        if(errors.isEmpty())
        return userService.login(userName,password);
        else return new ResponseEntity(errors.toMap(),HttpStatus.BAD_REQUEST);
    }
}

