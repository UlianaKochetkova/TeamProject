package com.example.TeamProject.controller;

import com.example.TeamProject.entities.Message;
import com.example.TeamProject.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Controller
public class ChatController {
    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private MessageTagRepo messageTagRepoRepo;

    @Autowired
    private TagRepo tagRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserChatRepo userChatRepo;

    @GetMapping("/chat")
    public String chat(Model model){
        model.addAttribute("msg",messageRepo.findAll());
        List<Message> lst=messageRepo.findAll();
        for (int i=0; i<lst.size(); i++){
            System.out.println(lst.get(i).getUser().getUsername()+": "+lst.get(i).getText());
        }
        return "chat";
    }

    @GetMapping("/chat1")
    public String chat1(Model model){
        List<Message> lst=messageRepo.findAll();
        lst.sort(Comparator.comparing(Message::getCreate_date));
        model.addAttribute("chat",chatRepo.findChatByTitle("chat1"));
        model.addAttribute("msgs", lst);
        return "chat1";
    }

    @PostMapping("/chat1")
    public String getMsg(Message msg, Model model){
        //Устанавливаем current time
        msg.setCreate_date(new Date());
        //Устанавливаем текущего юзера?
        //Пока установлю базового user1
        msg.setUser(userRepo.findUserByUsername("user1"));
        msg.setChat(chatRepo.findChatByTitle("chat1"));
        messageRepo.save(msg);
        return chat1(model);
    }

}
