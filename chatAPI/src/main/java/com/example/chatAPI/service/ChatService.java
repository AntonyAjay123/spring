package com.example.chatAPI.service;

import com.example.chatAPI.Util.ChatUtil;
import com.example.chatAPI.model.Chat;
import com.example.chatAPI.repository.ChatRepository;
import com.example.chatAPI.repository.StatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ChatService {
    @Autowired
    ChatRepository chatRepository;

    @Autowired
    ChatUtil chatUtil;

    @Autowired
    StatusRepository statusRepository;

    public int saveChat(Chat chat){
        chatRepository.save(chat);
        return chat.getChatId();
    }
    public JSONArray getToFrom(int to, int from){
        List<Chat> chats= chatRepository.getToFrom(to,from);
        log.info(chats.toString());
        JSONArray chatArray = new JSONArray();
        for(Chat chat: chats){
            JSONObject json = chatUtil.setJson(chat);
            chatArray.put(json);
        }
        return chatArray;
    }

    public JSONArray getChat(int userId1,int userId2){
        List<Chat> chats = chatRepository.getChat(userId1,userId2);
        JSONArray chatArray = new JSONArray();
        for(Chat chat: chats){
            JSONObject json = chatUtil.setJson(chat);
            chatArray.put(json);
        }
        return chatArray;
    }

    public void deleteChat(){
        chatRepository.deleteChat();
    }

    public  JSONArray getInbox(int id){
        List<Chat> inbox = chatRepository.getInbox(id);
        JSONArray chats = new JSONArray();
        for(Chat chat: inbox){
            JSONObject json = chatUtil.setInbox(chat);
            chats.put(json);
        }
        return chats;
    }

    public void deleteMessage(int id){
        Chat chat = chatRepository.findById(id).get();
        chat.setStatus(statusRepository.findById(2).get());
        chatRepository.save(chat);
    }
    public void updateMessage(int id,JSONObject json){
        Chat chat = chatRepository.findById(id).get();
        chat.setMessage(json.getString("message"));
        chatRepository.save(chat);
    }
}
