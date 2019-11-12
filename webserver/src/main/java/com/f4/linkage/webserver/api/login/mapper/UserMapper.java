package com.f4.linkage.webserver.api.login.mapper;

import com.f4.linkage.webserver.api.login.model.LoginUserInfo;
import com.f4.linkage.webserver.api.login.model.Role;
import com.f4.linkage.webserver.api.login.model.UserSelfInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
  LoginUserInfo loadUserByUserName(String username);
  List<Role> getUserRoleByUserId(Integer id);
  void changeTimeStamp(String username);
  UserSelfInfo getUserSelfInfoByUsername(String username);
  void updateSelfInfo(UserSelfInfo userSelfInfo);
}
