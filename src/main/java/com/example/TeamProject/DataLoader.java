package com.example.TeamProject;

import com.example.TeamProject.entities.*;
import com.example.TeamProject.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class DataLoader {
    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private MessageTagRepo messageTagRepo;

    @Autowired
    private TagRepo tagRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserChatRepo userChatRepo;

    @PostConstruct
    public void loadData(){
        Chat chat=new Chat();
        chat.setTitle("chat1");
        chat.setDescription("d1");
        chatRepo.save(chat);

        User u1=new User();
        User u2=new User();
        u1.setUsername("user1");
        u2.setUsername("user2");
        userRepo.save(u1);
        userRepo.save(u2);

        User_Chat uc=new User_Chat();

        uc.setUser(u1);
        uc.setChat(chat);
        userChatRepo.save(uc);

        User_Chat uc1=new User_Chat();
        uc1.setUser(u2);
        uc1.setChat(chat);
        userChatRepo.save(uc1);

        //Добавляю добавление тегов
        Tag tag1=new Tag();
        tag1.setName("tiktok");
        tag1.setChat(chat);
        Tag tag2=new Tag();
        tag2.setName("instagram");
        Tag tag3=new Tag();
        tag3.setName("vkontakte");
        Tag tag4=new Tag();
        tag4.setName("spam");
        tag2.setChat(chat);
        tag3.setChat(chat);
        tag4.setChat(chat);
        tagRepo.save(tag1);
        tagRepo.save(tag2);
        tagRepo.save(tag3);
        tagRepo.save(tag4);


        Message msg=new Message();
        msg.setChat(chat);
        msg.setUser(u1);
        msg.setText("Hello from user1. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ");
        GregorianCalendar cl=new GregorianCalendar(2021,6,23,16,0);
        msg.setCreate_date(cl.getTime());

        messageRepo.save(msg);

        Message msg1=new Message();
        msg1.setChat(chat);
        msg1.setUser(u2);
        GregorianCalendar cl1=new GregorianCalendar(2021,6,23,17,0);
        msg1.setCreate_date(cl1.getTime());
        msg1.setText("Hello from user2");
        messageRepo.save(msg1);


        Message_Tag mt=new Message_Tag();
        mt.setMessage(msg);
        mt.setTag(tag1);
        messageTagRepo.save(mt);

    }
}
