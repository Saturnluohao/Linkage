package com.f4.linkage.webserver.api.admin.controller;

import com.f4.linkage.webserver.api.admin.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @program: Linkage
 * @description: To lock or unlock the post
 * @author: Zijian Zhang
 * @create: 2019/11/13
 **/
@Controller
public class LockPostController {
  @Autowired
  private UserManagementService service;
  @GetMapping("/admin/post")
  public void getPostForAdmin(){

  }
  @PutMapping("/admin/post")
  public void unlockPost(){

  }
  @DeleteMapping("/admin/post")
  public void lockPost(){

  }
}
