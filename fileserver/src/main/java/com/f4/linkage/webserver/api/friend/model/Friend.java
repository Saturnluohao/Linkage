package com.f4.linkage.webserver.api.friend.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: Linkage
 * @description: The friend being added and searched
 * @author: Zijian Zhang
 * @create: 2019/11/08
 **/
@Data
public class Friend implements Serializable {
  private String username;
  private String description;
  private int sex;
}
