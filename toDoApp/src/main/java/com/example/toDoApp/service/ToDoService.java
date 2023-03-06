package com.example.toDoApp.service;

import com.example.toDoApp.model.Todo;
import com.example.toDoApp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class ToDoService {

    @Autowired
    TodoRepository todoRepository;
    static List<Todo> todos = new ArrayList<>();
    static int todoCount=0;

    public  List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public Todo findId(int id){
        return todoRepository.findById(id).get();
    }

    public void postTodo(Todo todo){
        todoRepository.save(todo);
    }

    public void deleteItem(int id){
        todoRepository.deleteById(id);
    }

    public void updateTodo(int id,Todo upTodo){
        Todo todo=todoRepository.findById(id).get();
        todo.setId(upTodo.getId());
        todo.setStatus(upTodo.isStatus());
        todo.setTitle(upTodo.getTitle());
        todoRepository.save(todo);

    }
}
