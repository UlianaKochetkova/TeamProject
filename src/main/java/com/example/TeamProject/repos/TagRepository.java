package com.example.TeamProject.repos;

import com.example.TeamProject.entities.Chat;
import com.example.TeamProject.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    List<Tag> findAllByChat_Id(Integer id);
    Tag findTagById(Integer id);
    Tag findTagByName(String name);
}
