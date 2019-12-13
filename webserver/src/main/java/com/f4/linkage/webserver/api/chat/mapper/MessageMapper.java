package com.f4.linkage.webserver.api.chat.mapper;

import com.f4.linkage.webserver.api.chat.model.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Mapper
@Repository
public interface MessageMapper {
  void addMessage(Message message);
  List<Message> getMessage(String userName,String targetName);
  List<Message> getUnreadMessage(String userName);
  Integer getLastInsertId();
  void setMessageReadById(Integer id);
  Date getDateById(Integer id);
}
