package com.example.TeamProject.controller;

import com.example.TeamProject.entities.*;
import com.example.TeamProject.repos.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
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
        cachedData.put("currTag", tagRepo.findTagByName("Main"));

        model.addAttribute("chats",chatRepo.findAll());
        model.addAttribute("chat", cachedData.get("currChat"));
        model.addAttribute("tags", tagRepo.findAllByChat((Chat)cachedData.get("currChat")));
        model.addAttribute("msgs", cachedData.get("msgs"));
        model.addAttribute("mt", messageTagRepo);
        return "new_chat";
    }

    @RequestMapping(value = "/getMsg", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String getMsg(@RequestParam("msg") String msgText, Model model){
    	Message msg = new Message();
    	msg.setText(msgText);
        //Устанавливаем current time
        msg.setCreate_date(new Date());
        //Устанавливаем текущего юзера?
        //Пока установлю базового user1
        msg.setUser(userRepo.findUserByUsername("user1"));
        msg.setChat((Chat)cachedData.get("currChat"));
        messageRepo.save(msg);
        Tag currTag = (Tag)cachedData.get("currTag");
        //Расставляем тэги
        science.Message scienceMessage = new science.Message(msg.getText());
        List<Message_Tag> messageTags = new ArrayList<>();
        boolean tagChanged = true;
        for (science.Tag messageTag : scienceMessage.getListMessageTags()) {
            Message_Tag message_tag = new Message_Tag();
            message_tag.setMessage(msg);
            message_tag.setTag(tagRepo.findTagByName(messageTag.name().toLowerCase()));
            if(message_tag.getTag().equals(currTag)) tagChanged = false;
            messageTags.add(message_tag);
        }
        messageTags.forEach(messageTagRepo::save);
        if(tagChanged && !currTag.equals(tagRepo.findTagByName("Main"))) {
        	return getTagMsgs(tagRepo.findTagByName("Main").getId().toString(), model);
        }
        else {
        	List<Message> msgs = (ArrayList<Message>) cachedData.get("msgs");
        	msgs.add(msg);
        	//без этого сообщения добавляются наверх
        	msgs.sort(Comparator.comparing(Message::getCreate_date).reversed());
        	cachedData.put("msgs", msgs);
        	return getTagMsgs(((Tag)cachedData.get("currTag")).getId().toString(), model);
        }
    }

///////////////////////////////   ЧАТ ПО ID ////////////////////////////////////////////////////
    //AJAX
    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String getChatAjax(@RequestParam("id") String id, Model model){
        // Запоминаем текущий чат, сообщения текущего чат и текущий тег в "кэше"
        cachedData.put("currChat", chatRepo.findChatById(Integer.parseInt(id)));
        List<Message> lst = messageRepo.findAllByChat((Chat)cachedData.get("currChat"));
        lst.sort(Comparator.comparing(Message::getCreate_date));
        cachedData.put("msgs", lst);
        cachedData.put("currTag", tagRepo.findTagByName("Main"));

        model.addAttribute("chat", cachedData.get("currChat"));
        model.addAttribute("tags", tagRepo.findAllByChat((Chat)cachedData.get("currChat")));
        model.addAttribute("msgs", cachedData.get("msgs"));
        model.addAttribute("mt", messageTagRepo);
        return "chat_template";
        ////////////////////////////////

//        //забрать сообщения по id
//        List<Message> lst=messageRepo.findAllByChat_Id(id);
//        lst.sort(Comparator.comparing(Message::getCreate_date).reversed());
//
//        model.addAttribute("chats",chatRepo.findAll());
//        model.addAttribute("chat",chatRepo.findChatById(id));
//        //теги нужно забрать по чату
//        model.addAttribute("tags",tagRepo.findAllByChat_Id(id));
//        model.addAttribute("msgs", lst);
//        model.addAttribute("mt",messageTagRepo);
//        model.addAttribute("users",userRepo.findAll());
//        return "new_chat";
    }


//    @GetMapping("/chat/{id}")
//    public String getChat(@PathVariable("id") Integer id, Model model){
//        //забрать сообщения по id
//        List<Message> lst=messageRepo.findAllByChat_Id(id);
//        lst.sort(Comparator.comparing(Message::getCreate_date).reversed());
//
//        model.addAttribute("chats",chatRepo.findAll());
//        model.addAttribute("chat",chatRepo.findChatById(id));
//        //теги нужно забрать по чату
//        model.addAttribute("tags",tagRepo.findAllByChat_Id(id));
//        model.addAttribute("msgs", lst);
//        model.addAttribute("mt",messageTagRepo);
//        model.addAttribute("users",userRepo.findAll());
//        return "new_chat";
//    }

//    @PostMapping("/chat/{id}")
//    public String getMsg(@PathVariable("id") Integer id,Message msg, Model model){
//        //Устанавливаем current time
//        msg.setCreate_date(new Date());
//        //Устанавливаем текущего юзера?
//        //Пока установлю базового user1
//        msg.setUser(userRepo.findUserByUsername("user1"));
//        msg.setChat(chatRepo.findChatById(id));
//        messageRepo.save(msg);
//
//        //Расставляем тэги
//        science.Message scienceMessage = new science.Message(msg.getText());
//        List<Message_Tag> messageTags = new ArrayList<>();
//        for (science.Tag messageTag : scienceMessage.getListMessageTags()) {
//            Message_Tag message_tag = new Message_Tag();
//            message_tag.setMessage(msg);
//            message_tag.setTag(tagRepo.findTagById(messageTag.ordinal()));
//            messageTags.add(message_tag);
//        }
//        messageTags.forEach(messageTagRepo::save);
//        //
//        return getChat(id,model);
//    }


////////////////////////////////  ТЕГИ  /////////////////////////////////////
/* Старый маппинг тегов
/////////////////////////////////////////////////////////////////////
    @GetMapping("/tag/{id}")
    public String getTag(@PathVariable("id") Integer id, Model model){
        //model.addAttribute("curtag",tagRepo.findTagById(id));
//        List<Message_Tag> mt=messageTagRepo.findAllByTag(tagRepo.findTagById(id));
        List<Message_Tag> mt=messageTagRepo.findAllByTag_Id(id);
        ArrayList<Message> tagmsg=new ArrayList<Message>();
        for (int i=0;i<mt.size(); i++){
            tagmsg.add(mt.get(i).getMessage());
            //System.out.println(tagmsg.get(i).getText());
        }
        tagmsg.sort(Comparator.comparing(Message::getCreate_date));
        model.addAttribute("chat",chatRepo.findChatByTitle("chat1"));
        model.addAttribute("tags",tagRepo.findAll());
        model.addAttribute("msgs",tagmsg);
        model.addAttribute("mt",messageTagRepo);
        return "new_chat";
    }
*/
    @RequestMapping(value = "/getTagMsgs", method = RequestMethod.GET)
    public String getTagMsgs(@RequestParam("tagid") String id, Model model){
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
        model.addAttribute("mt", messageTagRepo);
        model.addAttribute("msgs", cachedData.get("msgs"));
        return "msgs_template";
    }
}
