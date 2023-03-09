package com.example.chatAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatId;

    private int messageId;


    @JoinColumn(name = "from_user_id")
    @ManyToOne
    private User from;

    @JoinColumn(name = "to_user_id")
    @ManyToOne
    private User to;

    private String message;
    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private Timestamp createdDate;
    @UpdateTimestamp
    private Timestamp updateDate;

    @JoinColumn(name = "status_id")
    @ManyToOne
    private Status status;
}
