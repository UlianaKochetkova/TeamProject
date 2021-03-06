package com.example.TeamProject.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/***
 * Время отправки сообщения, юзер, сообщение
 */
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String title;
    String description;

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
        Chat o=(Chat)obj;

        if (!title.equals(o.title)){
            return false;
        }
        if (!description.equals(o.description)){
            return false;
        }
        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
