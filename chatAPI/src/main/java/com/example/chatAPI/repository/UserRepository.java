package com.example.chatAPI.repository;

import com.example.chatAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value="select * from tbl_user where user_name =:userName and status_id=1",nativeQuery = true)
    User findByUserName(String userName);

    @Query(value="select * from tbl_user where status_id=1",nativeQuery = true)
    List<User> activeUsers();

    @Query(value="select * from tbl_user where status_id=2",nativeQuery = true)
    List<User> inactiveUsers();
}
