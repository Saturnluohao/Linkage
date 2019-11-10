package com.f4.linkage.webserver.api.friend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @program: Linkage
 * @description: The request for add friends
 * @author: Zijian Zhang
 * @create: 2019/11/10
 **/
@Data
public class AddFriendRequest {
  private Integer id;
  private String username; // the man who send the request
  private String targetName; // the man who get the request
  private String selfIntro;
  @JsonIgnore
  private Boolean readStatus;
                              // 0 unread
                              // 1 read
  private Boolean acceptStatus;
                              // 0 accept
                              // 1 decline
  @JsonIgnore
  private Boolean replyStatus;
                              // 0 unread
                              // 1 read
}
