package com.f4.linkage.webserver.api.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: Linkage
 * @description: The chat message
 * @author: Zijian Zhang
 * @create: 2019/10/28
 **/
@Data
public class Message implements Serializable {
  private int id;
  private String name;
  private String content;
  private String to;
  private long timeStamp;
  @JsonIgnore
  private int status; // 0 unread
                      // 1 read
}
