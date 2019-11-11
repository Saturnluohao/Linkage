package com.f4.linkage.webserver.api.follow.model;

import lombok.Data;

/**
 * @program: Linkage
 * @description: The follow repationship between local and global users.
 * @author: Zijian Zhang
 * @create: 2019/11/11
 **/
@Data
public class FollowRelationship {
  private String localUsername;
  private String globalUsername;
}
