package com.example.chatAPI.service;

import com.example.chatAPI.Util.UserUtil;
import com.example.chatAPI.model.User;
import com.example.chatAPI.repository.StatusRepository;
import com.example.chatAPI.repository.UserRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    UserUtil userUtil;

    public int saveUser(User user){
        userRepository.save(user);
        return user.getUserId();
    }

    public JSONArray activeUsers(){
        List<User> active= userRepository.activeUsers();
        JSONArray users = new JSONArray();
        for(User user:active){
            JSONObject json=userUtil.setJson(user);
            users.put(json);
        }
        return users;
    }

    public JSONArray inactiveUsers(){
        List<User> active= userRepository.inactiveUsers();
        JSONArray users = new JSONArray();
        for(User user:active){
            JSONObject json=userUtil.setJson(user);
            users.put(json);
        }
        return users;
    }

    public void deleteUser(int userId){
        User user = userRepository.findById(userId).get();
        log.info(user.toString());
        user.setStatus(statusRepository.findById(2).get());
        log.info(user.toString());
        userRepository.save(user);
    }

    public JSONArray findAll(){
        List<User> users = userRepository.findAll();
        JSONArray array=new JSONArray();
        for(User user: users){
            JSONObject json=userUtil.setJson(user);
            array.put(json);
        }
        return array;
    }

    public ResponseEntity login(String userName, String password){
        User user = userRepository.findByUserName(userName);
        if(user==null)
            return new ResponseEntity("User name does not exist", HttpStatus.BAD_REQUEST);
        else{
            String storedPassword = user.getPassword();
            if(password.equals(storedPassword))
                return new ResponseEntity("Logged in successfully",HttpStatus.ACCEPTED);
            else
                return new ResponseEntity("Wrong password",HttpStatus.BAD_REQUEST);
        }
    }

}
