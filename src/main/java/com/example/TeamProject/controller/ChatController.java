package com.example.TeamProject.controller;

import com.example.TeamProject.entities.Message;
import com.example.TeamProject.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ChatController {
    @Autowired
    MessageRepo messageRepo;

    @GetMapping("/chat")
    public String chat(Model model){
        model.addAttribute("msg",messageRepo.findAll());
        List<Message> lst=messageRepo.findAll();
        for (int i=0; i<lst.size(); i++){
            System.out.println(lst.get(i).getUser().getUsername()+": "+lst.get(i).getText());
        }
        return "chat";
    }
}
