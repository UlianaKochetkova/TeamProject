package com.example.TeamProject.repos;

import com.example.TeamProject.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleById(Integer id);
    Role findRoleByName(String name);
}
