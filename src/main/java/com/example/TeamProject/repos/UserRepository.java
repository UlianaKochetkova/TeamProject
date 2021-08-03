package com.example.TeamProject.repos;

import com.example.TeamProject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
    User findUserById(Integer id);
}
