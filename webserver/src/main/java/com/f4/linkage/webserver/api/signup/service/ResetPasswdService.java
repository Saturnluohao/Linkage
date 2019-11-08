package com.f4.linkage.webserver.api.signup.service;
import com.f4.linkage.webserver.api.signup.mapper.ChangePasswdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.f4.linkage.webserver.api.signup.model.ForgetUserInfo;
import org.springframework.stereotype.Service;

/**
 * @program: Linkage
 * @description: Reset passwd to new value
 * @author: Zijian Zhang
 * @create: 2019/11/08
 **/
@Service
public class ResetPasswdService {
  private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
  @Autowired
  ChangePasswdMapper mapper;
  public void resetPasswd(ForgetUserInfo forgetUserInfo){
    String passwd = forgetUserInfo.getUserPassword().trim();
    passwd = bCryptPasswordEncoder.encode(passwd);
    forgetUserInfo.setUserPassword(passwd);
    mapper.changePasswdByPhone(forgetUserInfo.getPhoneNumber(),forgetUserInfo.getUserPassword());
  }
  public String getUserName(String phone){
    return mapper.getUserNameByPhone(phone);
  }
}
