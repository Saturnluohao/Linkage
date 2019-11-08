package com.f4.linkage.webserver.api.signup.service;

import com.f4.linkage.webserver.api.signup.error.SameNameError;
import com.f4.linkage.webserver.api.signup.error.SamePhoneNumberError;
import com.f4.linkage.webserver.api.signup.mapper.SignUpUserMapper;
import com.f4.linkage.webserver.api.signup.model.SignUpUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: Linkage
 * @description: Add the user to database
 * @author: Zijian Zhang
 * @create: 2019/11/07
 **/
@Service
public class AddUserService {
  @Autowired
  private SignUpUserMapper mapper;
  private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

  @Transactional
  public void storeNewUser(SignUpUserInfo upUserInfo) throws SameNameError,SamePhoneNumberError {
    int sameName = mapper.findSameName(upUserInfo.getUserName());
    if(sameName!=0){
      throw new SameNameError();
    }
    int samePhone = mapper.findSamePhoneNumber(upUserInfo.getPhoneNumber());
    if(samePhone!=0){
      throw new SamePhoneNumberError();
    }
    String passwd = upUserInfo.getUserPassword().trim();
    passwd = bCryptPasswordEncoder.encode(passwd);
    upUserInfo.setUserPassword(passwd);
    mapper.storeNewUser(upUserInfo);
    int id = mapper.getIDbyUserName(upUserInfo.getUserName());
    mapper.setRoleByID(id);
  }
}
