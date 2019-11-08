package com.f4.linkage.webserver.api.signup.model;

import lombok.Data;

/**
 * @program: Linkage
 * @description: The user info when forget passwd
 * @author: Zijian Zhang
 * @create: 2019/11/08
 **/
@Data
public class ForgetUserInfo {
  private String phoneNumber;
  private String userPassword;
  private String verificationCode;
}
