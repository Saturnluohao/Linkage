package com.f4.linkage.webserver.api.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: Linkage
 * @description: To redirect main page.
 * @author: Zijian Zhang
 * @create: 2019/10/26
 **/
@Controller
public class MainPageRedirect{
  @RequestMapping("/")
  public String index() {
    //has to be without blank spaces
    return "forward:index.html";
  }
}
