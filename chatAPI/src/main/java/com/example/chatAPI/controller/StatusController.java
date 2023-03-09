package com.example.chatAPI.controller;

import com.example.chatAPI.model.Status;
import com.example.chatAPI.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/status")
public class StatusController {
    @Autowired
    StatusService statusService;

    @PostMapping("/")
    public ResponseEntity saveStatus(@RequestBody Status status){
        return statusService.saveStatus(status);
    }
}
