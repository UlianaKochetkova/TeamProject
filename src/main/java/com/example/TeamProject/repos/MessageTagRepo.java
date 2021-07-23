package com.example.TeamProject.repos;

import com.example.TeamProject.entities.Message_Tag;
import com.example.TeamProject.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageTagRepo extends JpaRepository<Message_Tag, Integer> {
    List<Message_Tag> findAllByTag(Tag t);
}
