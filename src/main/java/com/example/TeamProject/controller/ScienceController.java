package com.example.TeamProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import science.Message;

@Controller
public class ScienceController {

    @GetMapping("/science")
    public String chat(Model model) {
        Message message = new Message("Я ты он она vkontakte tiktok");
        Message message1 = new Message("Я ты он она vkontakte vk");
        Message message2 = new Message("Я ты он она spam instagram spam vkontakte tiktok spam instagram tiktok");
        model.addAttribute("message", message);
        model.addAttribute("message1", message1);
        model.addAttribute("message2", message2);
        return "science";
    }

}
