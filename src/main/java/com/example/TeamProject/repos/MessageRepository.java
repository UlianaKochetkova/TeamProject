package com.example.TeamProject.repos;

import com.example.TeamProject.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    Message findMessageById(Integer id);
    List<Message> findAllByChat_Id(Integer chatId);
    List<Message> findAllByChat(Chat chat);

}
