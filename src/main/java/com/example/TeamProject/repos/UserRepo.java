package com.example.TeamProject.repos;

import com.example.TeamProject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
}
