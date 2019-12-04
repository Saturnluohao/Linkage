package com.f4.linkage.webserver.api.chat.controller;

import com.f4.linkage.webserver.api.chat.model.Message;
import com.f4.linkage.webserver.api.chat.service.MessageService;
import com.f4.linkage.webserver.api.friend.service.FriendService;
import com.f4.linkage.webserver.config.OnlineUserHub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

/**
 * @program: Linkage
 * @description: Broadcast hello message.
 * @author: Zijian Zhang
 * @create: 2019/10/28
 **/
@Controller
public class GreetingController {
  private Logger logger = LoggerFactory.getLogger(GreetingController.class);
  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;
  @Autowired
  private OnlineUserHub onlineUserHub;
  @Autowired
  private MessageService messageService;
  @Autowired
  private FriendService friendService;


  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public Message greeting(Message message) throws Exception{
    System.out.println("message: "+message.getName()+":"+message.getContent());
    return message;
  }

  @MessageMapping("/chat")
  public void chat(Principal principal, Message message){
    boolean targetOnline = onlineUserHub.findUserByName(message.getTo());
    // System.out.println("From: "+principal.getName()+" TO: "+message.getTo());
    // System.out.println(message.getTo()+" is "+ targetOnline);
    String from = principal.getName();
    message.setName(from);
    List<String> userFriendList = friendService.getAllMyFriends(principal.getName());
    if(!userFriendList.contains(message.getTo())){
      return;
    }
    if(targetOnline){
      message.setStatus(1);
    }else {
      message.setStatus(0);
    }
    messageService.addMessage(message);
    message.setId(messageService.getLastId());
    logger.info("send a message to " + message.getTo());
    simpMessagingTemplate.convertAndSendToUser(message.getTo(),"/queue/chat",message);
  }

  @MessageMapping("/chat/confirm")
  public void makeChatInfoRead(Principal principal, Message message){
    if(!message.getTo().equals(principal.getName())) return;
    logger.info("No."+message.getId()+" message is read.");
    messageService.setMessageReadById(message.getId());
  }
}
