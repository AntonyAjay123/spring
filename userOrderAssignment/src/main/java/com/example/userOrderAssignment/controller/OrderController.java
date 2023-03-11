package com.example.userOrderAssignment.controller;

import com.example.userOrderAssignment.model.Order;
import com.example.userOrderAssignment.service.OrderService;
import jakarta.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity saveOrder(@RequestBody Order order){
        int id = orderService.saveOrder(order);
        return new ResponseEntity("Order saved with id "+id, HttpStatus.CREATED);
    }
    @GetMapping("/order")
    public ResponseEntity getOrder(@Nullable @RequestParam int id){
        if(id==null){
            JSONArray orders = orderService.findAll();
            return new ResponseEntity(orders.toList(),HttpStatus.OK);
        }
        else{
            JSONObject order = orderService.findById(id);
            return new ResponseEntity(order.toMap(),HttpStatus.OK)
        }
    }
}
