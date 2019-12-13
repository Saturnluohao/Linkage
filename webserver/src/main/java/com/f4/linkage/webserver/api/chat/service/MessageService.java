package com.f4.linkage.webserver.api.chat.service;

import com.f4.linkage.webserver.api.chat.mapper.MessageMapper;
import com.f4.linkage.webserver.api.chat.model.Message;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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
  public void addMessage(Message message){
    messageMapper.addMessage(message);
  }
  public PageInfo<Message> findUnreadMessages(String name,String targetName, int currentPage, int pageSize){
    PageHelper.startPage(currentPage,pageSize);
    List<Message> messages = messageMapper.getMessage(name,targetName);
    return new PageInfo<>(messages);
  }
  public int getLastId(){return messageMapper.getLastInsertId();}
  public void setMessageReadById(int id){messageMapper.setMessageReadById(id);}
  public long getTimeStampByID(int id){
    Date date = messageMapper.getDateById(id);
    return date.getTime();
  }
}

