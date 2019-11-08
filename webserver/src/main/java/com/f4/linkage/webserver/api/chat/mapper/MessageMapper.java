package com.f4.linkage.webserver.api.chat.mapper;

import com.f4.linkage.webserver.api.chat.model.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MessageMapper {
  int addMessage(Message message);
  List<Message> getUnreadMessage(String receiverName);
  int setMessagesRead(String receiverName);
  // TODO add pagination
}
