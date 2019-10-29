package com.f4.linkage.webserver.api.chat.service;

import com.f4.linkage.webserver.api.chat.mapper.MessageMapper;
import com.f4.linkage.webserver.api.chat.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: Linkage
 * @description: Store and retrive messages.
 * @author: Zijian Zhang
 * @create: 2019/10/29
 **/
@Service
public class MessageService {
  @Autowired
  MessageMapper messageMapper;
  public int addMessage(Message message){
    return messageMapper.addMessage(message);
  }
  public List<Message> findUnreadMessages(String name){
    return messageMapper.getUnreadMessage(name);
  }
  public int setMessageAllRead(String name){
    return messageMapper.setMessagesRead(name);
  }
}
