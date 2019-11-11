package com.f4.linkage.webserver.api.globalUser.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: Linkage
 * @description: The relationship between local and global users
 * @author: Zijian Zhang
 * @create: 2019/11/11
 **/
@Data
public class LocalGlobalRelationship implements Serializable {
  private String localUserName;
  private String globalUserName;
}
