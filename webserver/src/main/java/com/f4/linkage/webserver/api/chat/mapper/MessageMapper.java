package com.f4.linkage.webserver.api.chat.mapper;

import com.f4.linkage.webserver.api.chat.model.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MessageMapper {
  int addMessage(Message message);
  List<Message> getMessage(String userName,String targetName);
  int setMessagesRead(String receiverName);
  List<Message> getUnreadMessage(String userName);
}
