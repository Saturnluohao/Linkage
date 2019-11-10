package com.f4.linkage.webserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
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
  @Autowired
  RedisTemplate<String,String> redisTemplate;

  private Set<String> userHub = new HashSet<>();
  public void userConnect(String name){
    SetOperations<String,String> operations = redisTemplate.opsForSet();
    operations.add("online",name);
  }
  public void userDisconnect(String name){
    SetOperations<String,String> operations = redisTemplate.opsForSet();
    operations.remove("online",name);
  }
  public boolean findUserByName(String name){
    SetOperations<String,String> operations = redisTemplate.opsForSet();
    return operations.isMember("online",name);
  }
}
