package com.example.chatAPI.repository;

import com.example.chatAPI.model.Chat;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {

    @Query(value = "select * from tbl_chat where to_user_id =:to and from_user_id =:from and status_id = 1",
            nativeQuery = true)
    List<Chat> getToFrom(int to, int from);
    //@Query(value = "select * from tbl_chat where to_user_id=:userId1 or to_user_id=:userId2 or from_user_id=:userId1 or from_user_id=:userId2",nativeQuery = true)
    @Query(value = "select * from tbl_chat where to_user_id in (:userId1,:userId2) and from_user_id in (:userId1,:userId2) and status_id=1",
            nativeQuery = true)
    List<Chat> getChat(int userId1,int userId2);

    @Modifying
    @Transactional
    @Query(value="delete from tbl_chat",
            countQuery = "select * from tbl_chat",
            nativeQuery = true)
    void deleteChat();

    @Query(value = "select * from tbl_chat where to_user_id=:id and status_id = 1",nativeQuery = true)
    List<Chat> getInbox(int id);
}
