package com.example.TeamProject;

import com.example.TeamProject.entities.*;
import com.example.TeamProject.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class DataLoader {
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private UserRoleRepository userRoleRepo;

    @PostConstruct
    public void loadData(){
        Chat chat=new Chat();
        chat.setTitle("Student chat");
        chat.setDescription("Chat with students");
        chatRepo.save(chat);

//        Chat chat2=new Chat();
//        chat2.setTitle("chat2");
//        chat2.setDescription("d2");
//        chatRepo.save(chat2);

        Role r1=new Role("User");
        Role r2=new Role("Admin");
        roleRepo.save(r1);
        roleRepo.save(r2);

        User u1=new User();
        User u2=new User();
        User u3 = new User();
        User u4 = new User();
        u1.setUsername("Dasha");
        u1.setPhoneNum("+12345678901");
        u1.setPassword(passwordEncoder.encode("admin"));
        u2.setUsername("Maxim Ka");
        u2.setPhoneNum("+98765432109");
        u2.setPassword(passwordEncoder.encode("user"));
        u3.setUsername("Max");
        u3.setPhoneNum("+23456789012");
        u3.setPassword(passwordEncoder.encode("user1"));
        u4.setUsername("Ulyana");
        u4.setPhoneNum("+34567890123");
        u4.setPassword(passwordEncoder.encode("user2"));
        userRepo.save(u1);
        userRepo.save(u2);
        userRepo.save(u3);
        userRepo.save(u4);

        User_Roles ur=new User_Roles();
        ur.setUser(u1);
        ur.setRole(roleRepo.findRoleByName("Admin"));
        userRoleRepo.save(ur);

        User_Roles ur1=new User_Roles();
        ur1.setUser(u2);
        ur1.setRole(roleRepo.findRoleByName("User"));
        userRoleRepo.save(ur1);

        User_Roles ur2 = new User_Roles();
        ur1.setUser(u3);
        ur1.setRole(roleRepo.findRoleByName("User"));
        userRoleRepo.save(ur1);

        User_Roles ur3 = new User_Roles();
        ur1.setUser(u4);
        ur1.setRole(roleRepo.findRoleByName("User"));
        userRoleRepo.save(ur1);

        User_Chat uc=new User_Chat();

        uc.setUser(u1);
        uc.setChat(chat);
        userChatRepo.save(uc);

        User_Chat uc1=new User_Chat();
        uc1.setUser(u2);
        uc1.setChat(chat);
        userChatRepo.save(uc1);

        ChatEmulator chatEmulator = new ChatEmulator(List.of(u1, u2, u3, u4), chat);

        chatEmulator.emulateChat();
        
//        Tag tag0=new Tag();
//        tag0.setName("Main");
//        tag0.setChat(chat);
//        tagRepo.save(tag0);
    }

    private class ChatEmulator {
        private List<User> users;
        private Chat chat;
        private Random random;

        ChatEmulator(List<User> users, Chat chat) {
            this.users = users;
            this.chat = chat;
            this.random = new Random(chat.getTitle().hashCode());
        }

        private void emulateChat() {
            Application.messages = new ArrayList<>();
            readFile();
        }

        private void emulateMessageSending(String messageText) {
            Message message = new Message();
            message.setUser(users.get(random.nextInt(4)));
            message.setText(messageText);
            message.setChat(chat);
            message.setCreate_date(new Date());

            Application.messages.add(messageText);

            messageRepo.save(message);
        }

        void readFile() {
            InputStream inputStream = getClass().getResourceAsStream("/dictionaries/chat.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            try {
                while (bufferedReader.ready()) {
                    String line = bufferedReader.readLine();
                    emulateMessageSending(line);
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
