package com.f4.linkage.webserver.api.login.model;

import lombok.Data;

/**
 * @program: Linkage
 * @description: The user require his info
 * @author: Zijian Zhang
 * @create: 2019/11/11
 **/
@Data
public class UserSelfInfo {
  private String description;
  private int sex;
  private String iconUrl;
  private String address;
  private String phoneNumber;
  private String username;
}
