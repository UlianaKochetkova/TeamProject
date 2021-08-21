package com.example.TeamProject.controller;

import com.example.TeamProject.Application;
import com.example.TeamProject.Colors;
import com.example.TeamProject.entities.Chat;
import com.example.TeamProject.entities.Message;
import com.example.TeamProject.entities.Message_Tag;
import com.example.TeamProject.entities.Tag;
import com.example.TeamProject.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

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

    @Autowired
    private Colors colors;

    HashMap<String, Object> cachedData = new HashMap<String, Object>();

    //redirect для security
    @GetMapping
    public String redirect(Model model){
        return "redirect:/chat1";
    }

    @GetMapping("/chat1")
    public String chat1(Model model){
        // Запоминаем текущий чат, сообщения текущего чат и текущий тег в "кэше"
        cachedData.put("currChat", chatRepo.findChatByTitle("chat1"));
        List<Message> lst = messageRepo.findAllByChat((Chat)cachedData.get("currChat"));
        lst.sort(Comparator.comparing(Message::getCreate_date).reversed());
        cachedData.put("msgs", lst);
        cachedData.put("currTag", tagRepo.findByName("Main"));

        model.addAttribute("chats",chatRepo.findAll());
        model.addAttribute("chat", cachedData.get("currChat"));
        model.addAttribute("tags", tagRepo.findAllByChat((Chat)cachedData.get("currChat")));
        model.addAttribute("msgs", cachedData.get("msgs"));
        model.addAttribute("mt", messageTagRepo);
        return "new_chat";
    }

    @RequestMapping(value = "/getMsg", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String getMsg(@RequestParam("msg") String msgText, Model model, Authentication authentication){
    	Message msg = new Message();
    	msg.setText(msgText);
        //Устанавливаем current time
        msg.setCreate_date(new Date());
        //Устанавливаем текущего юзера?
        //Пока установлю базового user1
        //msg.setUser(userRepo.findUserByUsername("user1"));
        msg.setUser(userRepo.findUserByPhoneNum(authentication.getName()));
        msg.setChat((Chat)cachedData.get("currChat"));
        messageRepo.save(msg);
        Tag currTag = (Tag)cachedData.get("currTag");
        //Tags
        boolean tagChanged = true;
        Application.nlpManager.addMessage(msg.getText());

        for (science.Tag tag : Application.nlpManager.keyWordsCollector.getTopTags(2)) {
            if (!tagRepo.existsByName(tag.getLabel())) {
                Tag tagEntity = new Tag();
                tagEntity.setName(tag.getLabel());
                //Добавить цвет вот тут
                //tagEntity.setColor("#03fcdb");
                tagEntity.setColor(colors.getRandomColor());
                tagEntity.setChat((Chat)cachedData.get("currChat"));

                tagRepo.save(tagEntity);
            }
        }
        science.Message scienceMessage = new science.Message(msg.getText());

        List<Message_Tag> messageTags = new ArrayList<>();
        for (science.Tag messageTag : scienceMessage.getListMessageTags(3)) {
            Message_Tag message_tag = new Message_Tag();
            message_tag.setMessage(msg);
            message_tag.setTag(tagRepo.findByName(messageTag.getLabel()));
            if (message_tag.getTag() != null) {
                if (message_tag.getTag().equals(currTag)) {
                    tagChanged = false;
                }
                messageTags.add(message_tag);
            }
        }
        messageTags.forEach(messageTagRepo::save);
        recomputeTags(((Chat) cachedData.get("currChat")).getId());
        // Обновляем текущий тег
        if(tagChanged && !currTag.equals(tagRepo.findByName("Main"))) {
        	setCurrTag(tagRepo.findByName("Main").getId().toString(), model);
        }
        else {
        	List<Message> msgs = (ArrayList<Message>) cachedData.get("msgs");
        	msgs.add(msg);
        	//без этого сообщения добавляются наверх
        	msgs.sort(Comparator.comparing(Message::getCreate_date).reversed());
        	cachedData.put("msgs", msgs);
        	setCurrTag(((Tag)cachedData.get("currTag")).getId().toString(), model);
        }
        
        
        model.addAttribute("chat", cachedData.get("currChat"));
        model.addAttribute("tags", tagRepo.findAllByChat((Chat)cachedData.get("currChat")));
        model.addAttribute("msgs", cachedData.get("msgs"));
        model.addAttribute("mt", messageTagRepo);
        model.addAttribute("msgs_count", messageRepo.findAllByChat((Chat)cachedData.get("currChat")).size());
        return "chat_template";
    }

///////////////////////////////   ЧАТ ПО ID ////////////////////////////////////////////////////
    //AJAX
//    @RequestMapping(value = "/chat", method = RequestMethod.GET)
//    public String getChatAjax(@RequestParam("id") String id, Model model){
//        // Запоминаем текущий чат, сообщения текущего чат и текущий тег в "кэше"
//        cachedData.put("currChat", chatRepo.findChatById(Integer.parseInt(id)));
//        List<Message> lst = messageRepo.findAllByChat((Chat)cachedData.get("currChat"));
//        lst.sort(Comparator.comparing(Message::getCreate_date));
//        cachedData.put("msgs", lst);
//        cachedData.put("currTag", tagRepo.findByName("Main"));
//
//        model.addAttribute("chat", cachedData.get("currChat"));
//        model.addAttribute("tags", tagRepo.findAllByChat((Chat)cachedData.get("currChat")));
//        model.addAttribute("msgs", cachedData.get("msgs"));
//        model.addAttribute("mt", messageTagRepo);
//        model.addAttribute("msgs_count", messageRepo.findAllByChat((Chat)cachedData.get("currChat")).size());
//        return "chat_template";
//    }

////////////////////////////////  ТЕГИ  /////////////////////////////////////

    @RequestMapping(value = "/setCurrTag", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String setCurrTag(@RequestParam("tagid") String id, Model model){
        int tId = Integer.parseInt(id);
        Tag currTag = tagRepo.findById(tId).get();
        Tag prevTag = (Tag)cachedData.get("currTag");
        if (!prevTag.equals(currTag))
        {
            System.out.println("NEW TAG! "+prevTag.getName()+"->"+currTag.getName());
            List<Message> tagmsg = new ArrayList<Message>();
            if (currTag.getName().equals("Main")) {
            	tagmsg = messageRepo.findAll();
            }
            else {
            	List<Message_Tag> mt = messageTagRepo.findAllByTag_Id(tId);
                for (int i=0;i < mt.size(); i++){
                    tagmsg.add(mt.get(i).getMessage());
                }
            }
            //Не уверена, что здесь нужен reversed, но поставила
            tagmsg.sort(Comparator.comparing(Message::getCreate_date).reversed());
            cachedData.put("msgs", tagmsg);
        }
        cachedData.put("currTag", currTag);
        return "index";
    }
    
    @RequestMapping(value = "/getTagMsgs", method = RequestMethod.GET)
    public String getTagMsgs(Model model){
        model.addAttribute("mt", messageTagRepo);
        model.addAttribute("msgs", cachedData.get("msgs"));
        return "msgs_template";
    }

    private void recomputeTags(Integer chatId) {
        for (Message message : messageRepo.findAllByChat_Id(chatId)) {
            science.Message scienceMessage = new science.Message(message.getText());

            List<Message_Tag> messageTags = new ArrayList<>();
            for (science.Tag messageTag : scienceMessage.getListMessageTags(3)) {
                Tag tag = tagRepo.findByName(messageTag.getLabel());
                if (tag != null) {
                    if (messageTagRepo.findByMessage_IdAndTag_Id(message.getId(), tag.getId()) == null) {
                        Message_Tag message_tag = new Message_Tag();
                        message_tag.setMessage(message);
                        message_tag.setTag(tag);
                        if (message_tag.getTag() != null) {
                            messageTags.add(message_tag);
                        }
                    }
                }
            }
            messageTags.forEach(messageTagRepo::save);
        }
    }
}
