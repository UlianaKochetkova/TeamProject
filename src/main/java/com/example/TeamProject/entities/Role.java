package com.example.TeamProject.entities;

import javax.persistence.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "role")
    public Set<User_Roles> roles;

    public Set<User_Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<User_Roles> roles) {
        this.roles = roles;
    }


    public Role(){
    }
    public Role(String name){
        this.name=name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

