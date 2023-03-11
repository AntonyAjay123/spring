package com.example.userOrderAssignment.service;

import com.example.userOrderAssignment.model.Order;
import com.example.userOrderAssignment.repository.OrderRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public int saveOrder(Order order){
        orderRepository.save(order);
        return order.getOrderId();
    }
    public JSONArray findAll(){
        List<Order> orders = orderRepository.findAll();
        JSONArray jsonArray = new JSONArray();
        for(Order order:orders){
            JSONObject json = new JSONObject(order);
            jsonArray.put(json);
        }
        return jsonArray;
    }

    public JSONObject findById(int id){
        Order order = orderRepository.findById(id).get();
        return new JSONObject(order);
    }
}
