package com.example.UserProject.service;

import com.example.UserProject.model.User;
import com.example.UserProject.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(int id){
        return userRepository.findById(id).get();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }

    public void updateUser(User newUser){

        User existingUser=findById(newUser.getId());
        JSONObject json=new JSONObject(newUser);
        if(json.has("userName"))
            existingUser.setUserName(newUser.getUserName());
        if(json.has("email"))
            existingUser.setEmail(newUser.getEmail());
        if(json.has("dateOfBirth"))
            existingUser.setDateOfBirth(newUser.getDateOfBirth());
        if(json.has("phoneNumber"))
            existingUser.setPhoneNumber(newUser.getPhoneNumber());
        userRepository.save(existingUser);
    }
}
