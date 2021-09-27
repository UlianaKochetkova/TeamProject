package com.example.TeamProject.controller;

import com.example.TeamProject.entities.User;
import com.example.TeamProject.entities.User_Roles;
import com.example.TeamProject.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
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

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private UserRoleRepository userRoleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registration(String msg, Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model){
        User found=userRepo.findUserByPhoneNum(user.getPhoneNum());
        if (found!=null){
            model.addAttribute("message","User with this phone already exists");
            return "registration";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);

        User_Roles ur=new User_Roles();
        ur.setUser(user);
        ur.setRole(roleRepo.findRoleByName("User"));
        userRoleRepo.save(ur);

        return "redirect:/login";
    }
}
