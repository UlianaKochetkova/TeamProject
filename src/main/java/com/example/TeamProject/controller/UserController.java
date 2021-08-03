package com.example.TeamProject.controller;

import com.example.TeamProject.entities.User;
import com.example.TeamProject.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
    @Autowired
    private ChatRepository chatRepo;

    @Autowired
    private MessageRepository messageRepo;

    @Autowired
    private MessageTagRepository messageTagRepo;

    @Autowired
    private TagRepository tagRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserChatRepository userChatRepo;

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable("id") Integer id, Model model){
        User user = userRepo.findUserById(id);
        model.addAttribute("user",user);
        return "user";
    }
}
