package com.example.instagramProject.service;

import com.example.instagramProject.model.Post;
import com.example.instagramProject.model.User;
import com.example.instagramProject.repository.PostRepository;
import com.example.instagramProject.repository.UserRepository;
import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    public JSONArray findAll(){
        JSONArray array = new JSONArray();
        List<Post> posts = postRepository.findAll();
        for(Post post:posts){
            JSONObject json = new JSONObject();
            json.put("postId",post.getPostId());
            json.put("createdDate",post.getCreatedDate());
            json.put("updatedDate",post.getUpdatedDate());
            json.put("user",post.getUser().getUserId());
            json.put("postData",post.getPostData());
            array.put(json);
        }
        return array;
    }

    public ResponseEntity findById(int id){
        JSONObject json = new JSONObject();
        if(postRepository.existsById(id)){
            Post post=postRepository.findById(id).get();
            json.put("postId",post.getPostId());
            json.put("createdDate",post.getCreatedDate());
            json.put("updatedDate",post.getUpdatedDate());
            json.put("user",post.getUser().getUserId());
            json.put("postData",post.getPostData());
            return new ResponseEntity(json.toString(),HttpStatus.OK);
        }
        else return new ResponseEntity("no such post found",HttpStatus.BAD_REQUEST);
    }


    public Boolean validPost(JSONObject json){
        List<String> errors = new ArrayList<>();
        if(json.has("userId")) {
            if (!userRepository.findById(json.getInt("user")).isPresent())
                return false;
        }
        return true;
    }

    public ResponseEntity setPost(JSONObject json, Integer id){
        Post post;
        if(id==null)
            post = new Post();
        else{
            if(postRepository.findById(id).isPresent())
                post = postRepository.findById(id).get();
            else {
                return new ResponseEntity("Enter valid post id",HttpStatus.BAD_REQUEST);
            }
        }
        if(json.has("postData"))
            post.setPostData(json.getString("postData"));
        Long time=System.currentTimeMillis();
        User user=null;
        if(id==null)
            post.setCreatedDate(new Timestamp(time));
        if(id!=null)
            post.setUpdatedDate(new Timestamp(time));
        if(json.has("user")){
            if(validPost(json)){
                user = userRepository.findById(json.getInt("user")).get();
                post.setUser(user);
            }
            else return new ResponseEntity<>("Enter valid user id",HttpStatus.OK);
        }
        postRepository.save(post);
        if(id==null)
        return new ResponseEntity("Post saved successfully",HttpStatus.CREATED);
        else return new ResponseEntity("Post update successfull", HttpStatus.OK);
    }

    public List<Post> findByUserId(int id){
        return postRepository.findByUser(userRepository.findById(id).get());
    }
}
