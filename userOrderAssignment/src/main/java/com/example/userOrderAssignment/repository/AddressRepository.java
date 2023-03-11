package com.example.userOrderAssignment.repository;

import com.example.userOrderAssignment.model.Address;
import com.example.userOrderAssignment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
}
