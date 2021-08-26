package com.example.TeamProject.repos;

import com.example.TeamProject.entities.Chat;
import com.example.TeamProject.entities.User_Chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChatRepository extends JpaRepository<User_Chat, Integer> {
	List<User_Chat> findAllByChat(Chat chat);
}
