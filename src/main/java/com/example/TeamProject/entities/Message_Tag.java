package com.example.TeamProject.entities;

import javax.persistence.*;

@Entity
public class Message_Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="message_id")
    private Message message;

    @ManyToOne
    @JoinColumn(name="tag_id")
    private Tag tag;

    public Integer getId() {
        return id;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

}
