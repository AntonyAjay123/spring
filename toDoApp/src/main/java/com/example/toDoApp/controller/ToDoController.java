package com.example.toDoApp.controller;

import com.example.toDoApp.model.Todo;
import com.example.toDoApp.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoController {

//    @RequestMapping("/message")
//    public String message(){
//        return "Hit the url";
//    }
//
//    @RequestMapping("/getdetails")
//    public String getDetails(@RequestParam String username,String password){
//        return "Hi "+username+" "+password;
//    }

    @Autowired
    ToDoService toDoService;
    @PostMapping("/")
    public void addTodo(@RequestBody Todo todo){
        toDoService.postTodo(todo);
    }

    @GetMapping("/findId")
    public Todo findById(@RequestParam int id){
        return toDoService.findId(id);
    }

    @GetMapping("/")
    public List<Todo> getAllTodo(){
        return  toDoService.getTodos();
    }

    @PutMapping("/updateTodo")
     public void updateTodo(@RequestParam int id,@RequestBody Todo todo){
        toDoService.updateTodo(id,todo);
    }

    @DeleteMapping("/deleteId")
    public void deleteItem(@RequestParam int id){
        toDoService.deleteItem(id);
    }
}
