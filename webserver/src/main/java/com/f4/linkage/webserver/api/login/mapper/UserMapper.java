package com.f4.linkage.webserver.api.login.mapper;

import com.f4.linkage.webserver.api.login.model.Role;
import com.f4.linkage.webserver.api.login.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
  User loadUserByUserName(String username);
  List<Role> getUserRoleByUserId(Integer id);
}
