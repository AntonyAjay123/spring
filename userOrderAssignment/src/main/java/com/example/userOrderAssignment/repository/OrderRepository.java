package com.example.userOrderAssignment.repository;

import com.example.userOrderAssignment.model.Order;
import com.example.userOrderAssignment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
}
