package com.example.TeamProject.repos;

import com.example.TeamProject.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepo extends JpaRepository<Chat, Integer> {
    Chat findChatByTitle(String title);
}
