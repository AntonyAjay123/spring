package com.example.userOrderAssignment.controller;

import com.example.userOrderAssignment.model.Address;
import com.example.userOrderAssignment.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {
    @Autowired
    AddressService addressService;

    @PostMapping("/address")
    public ResponseEntity saveAddress(@RequestBody Address address){
        int id = addressService.saveAddress(address);
        return new ResponseEntity("Address saved with id "+id, HttpStatus.CREATED);
    }
}
