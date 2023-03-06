package com.example.instagramProject.controller;

import com.example.instagramProject.model.Post;
import com.example.instagramProject.service.PostService;
import jakarta.annotation.Nullable;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping("/post")
    public ResponseEntity savePost(@RequestBody String post){
        JSONObject json = new JSONObject(post);
        return postService.setPost(json,null);
    }

    @GetMapping("/post")
    public ResponseEntity getPosts(@Nullable @RequestParam Integer id,@Nullable @RequestParam Integer userId){
        if(userId==null) {
            if (id == null)
                return new ResponseEntity(postService.findAll().toString(), HttpStatus.OK);
            else
                return postService.findById(id);
        }
        else return new ResponseEntity(postService.findByUserId(userId),HttpStatus.OK);
    }

    @PutMapping("/post")
    public ResponseEntity updatePost(@RequestParam Integer postId,@RequestBody String post){
        JSONObject json = new JSONObject(post);
        return postService.setPost(json,postId);

    }


}
