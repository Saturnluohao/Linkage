package com.f4.linkage.webserver.api.signup.mapper;

import com.f4.linkage.webserver.api.signup.model.SignUpUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SignUpUserMapper {
  Integer findSamePhoneNumber(String phone);
  Integer findSameName(String username);
  void storeNewUser(SignUpUserInfo upUserInfo);
  Integer getIDbyUserName(String username);
  void setRoleByID(Integer id);
}
