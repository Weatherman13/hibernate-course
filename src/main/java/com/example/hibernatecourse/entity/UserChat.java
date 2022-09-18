package com.example.hibernatecourse.entity;

import com.example.hibernatecourse.entity.base.AuditableEntity;
import com.example.hibernatecourse.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users_chat")
public class UserChat extends AuditableEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    public void setUser(User user){
        this.user = user;
        this.user.getUserChats().add(this);
    }

    public void setChat(Chat chat){
        this.chat = chat;
        this.chat.getUserChats().add(this);
    }
}
