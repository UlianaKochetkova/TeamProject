package com.example.TeamProject.repos;

import com.example.TeamProject.entities.User;
import com.example.TeamProject.entities.User_Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<User_Roles, Integer> {
    User_Roles findUser_RolesByUser(User user);
    User_Roles findUser_RolesByUser_Id(Integer id);
}
