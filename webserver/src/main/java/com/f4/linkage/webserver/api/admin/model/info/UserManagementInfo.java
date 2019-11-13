package com.f4.linkage.webserver.api.admin.model.info;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: Linkage
 * @description: User info in admin's eyes
 * @author: Zijian Zhang
 * @create: 2019/11/13
 **/
@Data
public class UserManagementInfo {
  private String username;
  private boolean locked;
  private String description;
  private String address;
  private Timestamp lastLogIn;
}
