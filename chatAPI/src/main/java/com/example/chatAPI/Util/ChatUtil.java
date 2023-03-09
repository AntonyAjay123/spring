package com.example.chatAPI.Util;

import com.example.chatAPI.model.Chat;
import com.example.chatAPI.model.User;
import com.example.chatAPI.repository.ChatRepository;
import com.example.chatAPI.repository.StatusRepository;
import com.example.chatAPI.repository.UserRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChatUtil {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    UserUtil userUtil;

    @Autowired
    StatusRepository statusRepository;

    public JSONObject validUsers(int to,int from){
        JSONObject json = new JSONObject();
        if(to==from)
            json.put("error","to and from cannot be same");
        if(!userUtil.checkUser(to))
            json.put("to id","invalid");
        if(!userUtil.checkUser(from))
            json.put("from id","invalid");
        return json;
    }
    public JSONArray validChat(JSONObject json){
        JSONArray array = new JSONArray();
        JSONObject errors = new JSONObject();
        int to=0,from=0;
        if(json.has("to"))
        to = json.getInt("to");
        else errors.put("to id","missing parameter");
        if(json.has("from"))
            from = json.getInt("from");
        else errors.put("from id","missing parameter");
        if(!json.has("message"))
            errors.put("message","missing parameter");
        if(!errors.isEmpty())
        array.put(errors);
        if(errors.isEmpty()){
            JSONObject users = validUsers(to,from);
            if(users.isEmpty()){
                return array;
            }
            else array.put(users);
        }
        return array;
    }

    public Chat setChat(JSONObject json){
        Chat newChat = new Chat();
        newChat.setTo(userRepository.findById(json.getInt("to")).get());
        newChat.setFrom(userRepository.findById(json.getInt("from")).get());
        newChat.setMessage(json.getString("message"));
        newChat.setStatus(statusRepository.findById(1).get());
        return newChat;
    }
    public JSONObject setJson(Chat chat){
        JSONObject json = new JSONObject();
        json.put("to",chat.getTo().getUserId());
        json.put("from",chat.getFrom().getUserId());
        json.put("message",chat.getMessage());
        return json;
    }
    public JSONObject setInbox(Chat chat){
        JSONObject json = new JSONObject();
        json.put("from",chat.getFrom().getUserName());
        json.put("message",chat.getMessage());
        return json;
    }
}
