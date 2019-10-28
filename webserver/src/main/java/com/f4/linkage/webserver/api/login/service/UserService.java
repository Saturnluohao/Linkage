package com.f4.linkage.webserver.api.login.service;

import com.f4.linkage.webserver.api.login.mapper.UserMapper;
import com.f4.linkage.webserver.api.login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @program: Linkage
 * @description: User CRUD
 * @author: Zijian Zhang
 * @create: 2019/10/26
 **/
@Service
public class UserService implements UserDetailsService {
  @Autowired
  UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    User user = userMapper.loadUserByUserName(s);
    if(user == null){
      throw new UsernameNotFoundException("The account is not registered!");
    }
    user.setRoles(userMapper.getUserRoleByUserId(user.getId()));
    return user;
  }

}
