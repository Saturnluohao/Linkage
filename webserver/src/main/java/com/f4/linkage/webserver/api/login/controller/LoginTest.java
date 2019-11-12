package com.f4.linkage.webserver.api.login.controller;

import com.f4.linkage.webserver.api.login.model.LoginUserInfo;
import com.f4.linkage.webserver.api.login.model.UserSelfInfo;
import com.f4.linkage.webserver.api.login.service.UserService;
import com.f4.linkage.webserver.util.RestfulResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: Linkage
 * @description: Test login
 * @author: Zijian Zhang
 * @create: 2019/10/26
 **/
@RestController
public class LoginTest {
  @Autowired
  private UserService userService;

  @GetMapping("/admin/hello")
  public String adminHello(){
    LoginUserInfo loginUserInfo = ((LoginUserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    return "hello admin" + loginUserInfo.getUsername();
  }
  @GetMapping("/user/hello")
  public String userHello(){
    LoginUserInfo loginUserInfo = ((LoginUserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    return "hello loginUserInfo" + loginUserInfo.getUsername();
  }
  @GetMapping("/login/tip")
  public void askForLogin(HttpServletResponse httpServletResponse) throws IOException {
    Map<String, Object> map = new HashMap<>();
    map.put("msg","please login!");
    RestfulResponseHelper.writeToResponse(httpServletResponse,401,map);
  }
  @GetMapping("/isLog")
  public void askIfLog(HttpServletResponse response) throws IOException{
    RestfulResponseHelper.writeToResponse(response,200,null);
  }
  @GetMapping("/user/selfInfo")
  public void getUserSelfInformation(Principal principal,HttpServletResponse response) throws IOException {
    UserSelfInfo selfInfo = userService.getUserInfoByHimself(principal.getName());
    Map<String,Object> map = new HashMap<>();
    map.put("info",selfInfo);
    RestfulResponseHelper.writeToResponse(response,200,map);
  }
  @PutMapping("/user/selfInfo")
  public void setUserSelfInfo(Principal principal,HttpServletResponse response,String sex,String address,String description) throws IOException {
    int sexNum;
    try {
      sexNum = Integer.parseInt(sex);
    }catch (NumberFormatException e){
      Map<String,Object> map = new HashMap<>();
      map.put("msg","invalid sex");
      RestfulResponseHelper.writeToResponse(response,401,map);
      return;
    }
    String username = principal.getName();
    UserSelfInfo selfInfo = new UserSelfInfo();
    selfInfo.setUsername(username);
    selfInfo.setSex(sexNum);
    selfInfo.setAddress(address);
    selfInfo.setDescription(description);
    userService.updateUserInfo(selfInfo);
    Map<String,Object> map = new HashMap<>();
    map.put("msg","OK");
    RestfulResponseHelper.writeToResponse(response,200,map);
  }


}
