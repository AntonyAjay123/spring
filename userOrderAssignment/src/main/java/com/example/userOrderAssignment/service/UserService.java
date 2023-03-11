package com.example.userOrderAssignment.service;

import com.example.userOrderAssignment.model.User;
import com.example.userOrderAssignment.repository.UserRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void saveUser(User user){
        userRepository.save(user);
    }

    public JSONArray findAll(){
        JSONArray jsonArray = new JSONArray();
        List<User> users= userRepository.findAll();
        for(User user:users){
            JSONObject json = new JSONObject(user);
            jsonArray.put(json);
        }
        return jsonArray;
    }

    public JSONObject findUser(int id){
      User user = userRepository.findById(id).get();
      JSONObject jsonObject = new JSONObject(user);
      return jsonObject;
    }
}
