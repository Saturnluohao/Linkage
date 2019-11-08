package com.f4.linkage.webserver.api.signup.error;

/**
 * @program: Linkage
 * @description: The error if phone number is registered
 * @author: Zijian Zhang
 * @create: 2019/11/07
 **/
public class SamePhoneNumberError extends Error {
  public SamePhoneNumberError(){
    super("The phone number is already registered");
  }
}
