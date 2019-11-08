package com.f4.linkage.webserver.api.signup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @program: Linkage
 * @description: Validate user phone number
 * @author: Zijian Zhang
 * @create: 2019/11/07
 **/
@Service
public class ValidateUserPhoneService {
  @Autowired
  RedisTemplate<String,String> redisTemplate;
  public boolean validatePhoneNumber(String phone,String code,String type){
    HashOperations<String,String,String> operations = redisTemplate.opsForHash();
    String userCode = operations.get(type,phone);
    System.out.println(code);
    System.out.println(userCode);
    return userCode != null && userCode.equals(code);
  }
  public void deleteUsedCode(String phone,String type){
    HashOperations<String,String,String> operations = redisTemplate.opsForHash();
    operations.delete(type,phone);
  }
}
