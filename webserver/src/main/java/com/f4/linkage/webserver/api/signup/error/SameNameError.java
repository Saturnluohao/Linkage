package com.f4.linkage.webserver.api.signup.error;

/**
 * @program: Linkage
 * @description: the error if a name is registered
 * @author: Zijian Zhang
 * @create: 2019/11/07
 **/
public class SameNameError extends Error {
  public SameNameError(){
    super("The user name is already registered");
  }
}
