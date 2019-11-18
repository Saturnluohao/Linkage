package com.f4.linkage.webserver.api.login.model.unread;

import com.f4.linkage.webserver.api.chat.model.Message;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: Linkage
 * @description: Information about unread messages
 * @author: Zijian Zhang
 * @create: 2019/11/10
 **/
@Data
public class UnreadMessage implements Serializable {
  private int count;
  private List<Message> unreadList;
}
