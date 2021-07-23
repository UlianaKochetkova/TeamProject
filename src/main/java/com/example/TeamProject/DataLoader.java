package com.example.TeamProject;

import com.example.TeamProject.entities.*;
import com.example.TeamProject.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class DataLoader {
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
    }
}
