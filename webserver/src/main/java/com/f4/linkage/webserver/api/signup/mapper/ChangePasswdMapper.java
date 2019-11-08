package com.f4.linkage.webserver.api.signup.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ChangePasswdMapper {
  void changePasswdByPhone(String phone,String newPasswd);
  String getUserNameByPhone(String phone);
}
