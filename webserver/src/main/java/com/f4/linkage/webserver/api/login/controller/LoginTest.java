package com.f4.linkage.webserver.api.login.controller;

import com.f4.linkage.webserver.api.login.model.User;
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
    User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    return "hello admin" + user.getUsername();
  }
  @GetMapping("/user/hello")
  public String userHello(){
    User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    return "hello user" + user.getUsername();
  }
  @GetMapping("/login/tip")
  public void askForLogin(HttpServletResponse httpServletResponse) throws IOException {
    Map<String, Object> map = new HashMap<>();
    map.put("msg","please login!");
    RestfulResponseHelper.writeToResponse(httpServletResponse,401,map);
  }


}
