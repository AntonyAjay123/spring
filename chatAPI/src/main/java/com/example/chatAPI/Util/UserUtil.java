package com.example.chatAPI.Util;

import com.example.chatAPI.model.User;
import com.example.chatAPI.repository.StatusRepository;
import com.example.chatAPI.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class UserUtil {
    @Autowired
    UserRepository userRepository;
    @Autowired
    StatusRepository statusRepository;

    @Autowired
    CommonUtils commonUtils;
    public User setUser(JSONObject json, Integer id){
        List<String> errors = new ArrayList<>();
        User user;
        if(id==null) {
            user = new User();
            if(!json.has("statusId"))
            user.setStatus(statusRepository.findById(1).get());
            else user.setStatus(statusRepository.findById(2).get());
        }
        else {
            if(userRepository.findById(id).isPresent())
                user = userRepository.findById(id).get();
            else{
                return null;
            }
        }
        if(json.has("userName")) {
            String userName = json.getString("userName");
            User existing = userRepository.findByUserName(userName);
            if(existing==null)
            user.setUserName(userName);
            else errors.add("userName already exists");
        }
        if(json.has("age"))
            user.setAge(json.getInt("age"));
        if(json.has("email"))
            user.setEmail(json.getString("email"));
        if(json.has("gender"))
            user.setGender(json.getString("gender"));
        if(json.has("password"))
            user.setPassword(json.getString("password"));
        if(json.has("phoneNumber"))
            user.setPhoneNumber(json.getString("phoneNumber"));
        if(errors .isEmpty())userRepository.save(user);
        return user;
    }

    public List<String> validUser(JSONObject json){
        List<String> errors = new ArrayList<>();
        if(!json.has("userName"))
            errors.add("userName");
        else{
            String enteredUsername = json.getString("userName");
            log.info(enteredUsername);
            User exisitingUser = userRepository.findByUserName(enteredUsername);
            if(exisitingUser!=null)
                errors.add("userName already exists");
        }
        if(!json.has("age"))
            errors.add("age");
        if(!json.has("email"))
            errors.add("email");
        else{
            if(!commonUtils.isValidEmail(json.getString("email")))
                errors.add("Enter valid email address");
        }
        if(!json.has("gender"))
            errors.add("gender");
        if(!json.has("password"))
            errors.add("password");
        else{
            if(!commonUtils.isValidPassword(json.getString("password")))
                errors.add("Password should be at least 8 chars,one Upper,one lower,one special,one digit and no spaces");
        }
        if(!json.has("phoneNumber"))
            errors.add("phoneNumber");
        else{
            if(!commonUtils.isValidPhoneNumber(json.getString("phoneNumber")))
                errors.add("Enter valid phone number");
        }
        return errors;
    }

    public boolean checkUser(int id){
        if(userRepository.findById(id).isPresent())
            return true;
        else return false;
    }

    public JSONObject setJson(User user){
        JSONObject json = new JSONObject();
        json.put("userId",user.getUserId());
        json.put("userName",user.getUserName());
        json.put("age",user.getAge());
        json.put("gender",user.getGender());
        json.put("Email",user.getEmail());
        json.put("status",user.getStatus().getStatusName());
        return json;
    }

    public List<String> validUpdate(JSONObject json,Integer userId){
        List<String> errors = new ArrayList<>();
        if(json.has("userName")){
            String enteredUsername = json.getString("userName");
            log.info(enteredUsername);
            User exisitingUser = userRepository.findByUserName(enteredUsername);
            if(exisitingUser!=null && exisitingUser.getUserId()!=userId)
                errors.add("userName already exists");
        }
        if(json.has("email")){
            if(!commonUtils.isValidEmail(json.getString("email")))
                errors.add("Enter valid email address");
        }
        if(json.has("password")){
            if(!commonUtils.isValidPassword(json.getString("password")))
                errors.add("Password should be at least 8 chars,one Upper,one lower,one special,one digit and no spaces");
        }
        if(json.has("phoneNumber")){
            if(!commonUtils.isValidPhoneNumber(json.getString("phoneNumber")))
                errors.add("Enter valid phone number");
        }
        return errors;
    }
}
