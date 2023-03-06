package com.example.instagramProject.repository;

import com.example.instagramProject.model.Post;
import com.example.instagramProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
}
