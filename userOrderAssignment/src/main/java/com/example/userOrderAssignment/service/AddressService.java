package com.example.userOrderAssignment.service;

import com.example.userOrderAssignment.model.Address;
import com.example.userOrderAssignment.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public int saveAddress(Address address){
        addressRepository.save(address);
        return address.getAddressId();
    }
}
