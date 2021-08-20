package com.example.TeamProject.entities;

import javax.persistence.*;


@Entity
@Table(name="usrrole")
public class User_Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public User_Roles(){

    }
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;

    public User_Roles(User user, Role role){
        this.user=user;
        this.role=role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
