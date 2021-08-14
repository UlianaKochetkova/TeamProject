package com.example.TeamProject.repos;

import com.example.TeamProject.entities.Chat;
import com.example.TeamProject.entities.Message;
import com.example.TeamProject.entities.Message_Tag;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    Message findMessageById(Integer id);
	List<Message> findAllByChat(Chat chat);
}
