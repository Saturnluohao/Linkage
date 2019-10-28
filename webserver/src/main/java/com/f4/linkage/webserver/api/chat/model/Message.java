package com.f4.linkage.webserver.api.chat.model;

import lombok.Data;

/**
 * @program: Linkage
 * @description: The chat message
 * @author: Zijian Zhang
 * @create: 2019/10/28
 **/
@Data
public class Message {
  private String name;
  private String content;
  private String to;
}
