package com.f4.linkage.webserver.api.login.mapper;

import com.f4.linkage.webserver.api.login.model.LoginUserInfo;
import com.f4.linkage.webserver.api.login.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
  LoginUserInfo loadUserByUserName(String username);
  List<Role> getUserRoleByUserId(Integer id);
}
