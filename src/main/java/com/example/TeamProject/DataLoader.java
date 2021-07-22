package com.example.TeamProject;

import com.example.TeamProject.entities.*;
import com.example.TeamProject.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
        u1.setUsername("user2");
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
        msg.setText("Hello from user1");
        messageRepo.save(msg);

        Message msg1=new Message();
        msg1.setChat(chat);
        msg1.setUser(u2);
        msg1.setText("Hello from user2");
        messageRepo.save(msg1);
    }
}
