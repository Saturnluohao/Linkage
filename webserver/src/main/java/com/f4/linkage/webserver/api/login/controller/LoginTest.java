package com.f4.linkage.webserver.api.login.controller;

import com.f4.linkage.webserver.api.login.model.LoginUserInfo;
import com.f4.linkage.webserver.util.RestfulResponseHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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


}
