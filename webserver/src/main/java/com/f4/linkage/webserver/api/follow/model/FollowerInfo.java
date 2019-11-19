package com.f4.linkage.webserver.api.follow.model;

import lombok.Data;

/**
 * @program: Linkage
 * @description: The info of someone's follower
 * @author: Zijian Zhang
 * @create: 2019/11/19
 **/
@Data
public class FollowerInfo {
  String username;
  String iconUrl;
  String description;
}
