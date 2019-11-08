package com.f4.linkage.webserver.api.friend.model;

import lombok.Data;

/**
 * @program: Linkage
 * @description: The friend being added and searched
 * @author: Zijian Zhang
 * @create: 2019/11/08
 **/
@Data
public class Friend {
  private String username;
  private String description;
  private int sex;
}
