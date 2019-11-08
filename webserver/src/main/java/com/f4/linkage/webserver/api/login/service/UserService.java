package com.f4.linkage.webserver.api.login.service;

import com.f4.linkage.webserver.api.login.mapper.UserMapper;
import com.f4.linkage.webserver.api.login.model.LoginUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @program: Linkage
 * @description: LoginUserInfo CRUD
 * @author: Zijian Zhang
 * @create: 2019/10/26
 **/
@Service
public class UserService implements UserDetailsService {
  @Autowired
  UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    LoginUserInfo loginUserInfo = userMapper.loadUserByUserName(s);
    if(loginUserInfo == null){
      throw new UsernameNotFoundException("The account is not registered!");
    }
    loginUserInfo.setRoles(userMapper.getUserRoleByUserId(loginUserInfo.getId()));
    return loginUserInfo;
  }

}
