package com.example.TeamProject.entities;

import javax.persistence.*;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name="chat_id")
    Chat chat;

    String name;

    String color;

    @Override
    public boolean equals(Object obj) {
        if (obj==this){
            return true;
        }
        if (obj==null){
            return false;
        }
        if (this.getClass()!=obj.getClass())
            return false;
        Tag o=(Tag)obj;
        if (!chat.equals(o.chat)){
            return false;
        }
        if (!name.equals(o.name)){
            return false;
        }
        return true;
    }
    public Tag(){

    }

    public Tag(Integer id, Chat chat, String name) {
        this.id = id;
        this.chat = chat;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
