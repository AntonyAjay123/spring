package com.example.chatAPI.service;

import com.example.chatAPI.model.Status;
import com.example.chatAPI.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StatusService {
    @Autowired
    StatusRepository statusRepository;

    public ResponseEntity<String> saveStatus(Status status){
        statusRepository.save(status);
        return new ResponseEntity<>("Successfully saved status with id as "+status.getStatusId(), HttpStatus.CREATED);
    }
}
