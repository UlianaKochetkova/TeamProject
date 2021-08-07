package com.example.TeamProject.controller;

import com.example.TeamProject.entities.Message;
import com.example.TeamProject.entities.Message_Tag;
import com.example.TeamProject.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Controller
public class ChatController {
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
        lst.sort(Comparator.comparing(Message::getCreate_date).reversed());
        model.addAttribute("chat",chatRepo.findChatByTitle("chat1"));
        model.addAttribute("tags",tagRepo.findAll());
        model.addAttribute("msgs", lst);
        model.addAttribute("mt",messageTagRepo);
        return "new_chat";
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
        //Расставляем тэги
        science.Message scienceMessage = new science.Message(msg.getText());
        List<Message_Tag> messageTags = new ArrayList<>();
        for (science.Tag messageTag : scienceMessage.getListMessageTags()) {
            Message_Tag message_tag = new Message_Tag();
            message_tag.setMessage(msg);
            message_tag.setTag(tagRepo.findTagById(messageTag.ordinal()));
            messageTags.add(message_tag);
        }
        messageTags.forEach(messageTagRepo::save);
        //
        return chat1(model);
    }

/////////////////////////////////////////////////////////////////////
    @GetMapping("/tag/{id}")
    public String getTag(@PathVariable("id") Integer id, Model model){
        //model.addAttribute("curtag",tagRepo.findTagById(id));
//        List<Message_Tag> mt=messageTagRepo.findAllByTag(tagRepo.findTagById(id));
        List<Message_Tag> mt=messageTagRepo.findAllByTag_Id(id);
        ArrayList<Message> tagmsg=new ArrayList<Message>();
        for (int i=0;i<mt.size(); i++){
            tagmsg.add(mt.get(i).getMessage());
            System.out.println(tagmsg.get(i).getText());
        }
        tagmsg.sort(Comparator.comparing(Message::getCreate_date));
        model.addAttribute("chat",chatRepo.findChatByTitle("chat1"));
        model.addAttribute("tags",tagRepo.findAll());
        model.addAttribute("msgs",tagmsg);
        model.addAttribute("mt",messageTagRepo);
        return "new_chat";
    }


}
