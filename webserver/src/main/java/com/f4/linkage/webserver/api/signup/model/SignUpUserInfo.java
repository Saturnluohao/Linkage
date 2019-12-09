package com.f4.linkage.webserver.api.signup.model;

import lombok.Data;

/**
 * @program: Linkage
 * @description: The user info when sign up.
 * @author: Zijian Zhang
 * @create: 2019/11/07
 **/
@Data
public class SignUpUserInfo {
  private String phoneNumber;
  private String userName;
  private String userPassword;
  private String verificationCode;
  private String email;
}
