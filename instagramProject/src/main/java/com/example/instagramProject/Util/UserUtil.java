package com.example.instagramProject.Util;

import com.example.instagramProject.model.User;
import com.example.instagramProject.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserUtil {
    @Autowired
    UserRepository userRepository;
    public List<String> validUser(JSONObject json){
        List<String> errors = new ArrayList<>();
        if(!json.has("userName"))
            errors.add("Enter userName");
        if(!json.has("age"))
            errors.add("enter age");
        if(!json.has("email"))
            errors.add("enter email");
        if(!json.has("phoneNumber"))
            errors.add("enter phoneNumber");
        return errors;
    }
    public User setUser(JSONObject json, Integer id){
        User user;
        if(id==null)
            user = new User();
        else {
            if(userRepository.findById(id).isPresent())
                user = userRepository.findById(id).get();
            else{
                return null;
            }
        }
        if(json.has("userName"))
            user.setUserName(json.getString("userName"));
        if(json.has("age"))
            user.setAge(json.getInt("age"));
        if(json.has("email"))
            user.setEmail(json.getString("email"));
        if(json.has("phoneNumber"))
            user.setPhoneNumber(json.getString("phoneNumber"));
        if(id!=null)userRepository.save(user);
        return user;
    }
}
