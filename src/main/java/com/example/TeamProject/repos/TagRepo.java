package com.example.TeamProject.repos;

import com.example.TeamProject.entities.Chat;
import com.example.TeamProject.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepo extends JpaRepository<Tag, Integer> {
//    Задел на будущее
    List<Tag> findAllByChat(Chat chat);
    Tag findTagById(Integer id);
}
