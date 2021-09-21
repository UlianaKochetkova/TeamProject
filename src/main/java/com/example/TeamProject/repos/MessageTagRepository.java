package com.example.TeamProject.repos;

import com.example.TeamProject.entities.Message_Tag;
import com.example.TeamProject.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageTagRepository extends JpaRepository<Message_Tag, Integer> {
    List<Message_Tag> findAllByTag(Tag t);
    List<Message_Tag> findAllByMessage_Id(Integer id);
    List<Message_Tag> findAllByTag_Id(Integer id);

    Message_Tag findByMessage_IdAndTag_Id(Integer messageId, Integer tagId);
    List<Message_Tag> findAllByTag_NameIsNot(String name);
    List<Message_Tag> findAllByTag_Name(String tagName);
}
