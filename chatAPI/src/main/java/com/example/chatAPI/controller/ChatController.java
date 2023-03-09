package com.example.chatAPI.controller;

import com.example.chatAPI.Util.ChatUtil;
import com.example.chatAPI.model.Chat;
import com.example.chatAPI.service.ChatService;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Null;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/chat/")
public class ChatController {
    @Autowired
    ChatService chatService;
    @Autowired
    ChatUtil chatUtil;
    @PostMapping("/")
    public ResponseEntity saveChat(@RequestBody String chat){
        JSONObject chatJson = new JSONObject(chat);
        JSONArray errors = chatUtil.validChat(chatJson);
        if(errors.isEmpty()){
            Chat newChat = chatUtil.setChat(chatJson);
            int id=chatService.saveChat(newChat);
            return new ResponseEntity("message sent successfully. Message ID: "+id,HttpStatus.CREATED);
        }
        else return new ResponseEntity<>(errors.toList(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getChat")
    public ResponseEntity getChat(@Nullable @RequestParam Integer to,
                                  @Nullable @RequestParam Integer from,
                                  @Nullable @RequestParam Integer userId1,
                                  @Nullable @RequestParam Integer userId2){
        if(to!=null && from!=null){
            return new ResponseEntity(chatService.getToFrom(to,from).toList(),HttpStatus.OK);
        }
        else if(userId1!=null && userId2!=null){
            return new ResponseEntity(chatService.getChat(userId1,userId2).toList(),HttpStatus.OK);
        }
        return null;
    }

    @DeleteMapping("/")
    public void delete(){
        chatService.deleteChat();
    }

    @DeleteMapping("/deleteChat")
    public ResponseEntity deleteChat(@RequestParam int id){
        chatService.deleteMessage(id);
        return new ResponseEntity("Deleted successfully",HttpStatus.ACCEPTED);
    }

    @GetMapping("/inbox")
    public ResponseEntity getInbox(@RequestParam int id){
        return new ResponseEntity(chatService.getInbox(id).toList(),HttpStatus.OK);
    }


    @PutMapping("/chatId")
    public ResponseEntity updateMessage(@RequestParam int id,@RequestBody String message){
        JSONObject json = new JSONObject(message);
        chatService.updateMessage(id,json);
        return new ResponseEntity<>("Sucessfully updated",HttpStatus.OK);
    }
}
