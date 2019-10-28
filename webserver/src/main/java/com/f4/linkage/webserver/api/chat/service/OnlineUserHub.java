package com.f4.linkage.webserver.api.chat.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: Linkage
 * @description: Keep in tract of every online user.
 * @author: Zijian Zhang
 * @create: 2019/10/28
 **/
@Service
public class OnlineUserHub {
  private Set<String> userHub = new HashSet<>();
  public void userConnect(String name){
    userHub.add(name);
  }
  public void userDisconnect(String name){
    userHub.remove(name);
  }
  public boolean findUserByName(String name){
    return userHub.contains(name);
  }
}
