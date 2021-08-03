package com.example.TeamProject.repos;

import com.example.TeamProject.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    Message findMessageById(Integer id);
}
