package com.example.TeamProject.repos;

import com.example.TeamProject.entities.User_Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChatRepo extends JpaRepository<User_Chat, Integer> {
}
