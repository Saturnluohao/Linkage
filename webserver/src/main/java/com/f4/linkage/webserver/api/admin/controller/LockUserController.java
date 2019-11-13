package com.f4.linkage.webserver.api.admin.controller;

import com.f4.linkage.webserver.api.admin.model.info.UserManagementInfo;
import com.f4.linkage.webserver.api.admin.model.operation.LockUserOperation;
import com.f4.linkage.webserver.api.admin.service.UserManagementService;
import com.f4.linkage.webserver.util.RestfulResponseHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: Linkage
 * @description: To lock or unlock user
 * @author: Zijian Zhang
 * @create: 2019/11/13
 **/
@Controller
public class LockUserController {
  @Autowired
  private UserManagementService managementService;
  private static Logger logger = LoggerFactory.getLogger(LockUserController.class);

  @GetMapping("/admin/user")
  public void getUserInfoForAdmin(int currentPage, int pageSize, String username, HttpServletResponse response) throws IOException {
    Map<String,Object> map = new HashMap<>();
    PageInfo<UserManagementInfo> userManagementInfoPageInfo = managementService.searchForUserByUserName(username,currentPage,pageSize);
    map.put("totalPages",userManagementInfoPageInfo.getPages());
    map.put("totalNumber",userManagementInfoPageInfo.getTotal());
    map.put("userList",userManagementInfoPageInfo.getList());
    RestfulResponseHelper.writeToResponse(response,200,map);
  }

  @PutMapping("/admin/user")
  public void unLockUserByUsername(String username, String reason, Principal principal, HttpServletResponse response) throws IOException {
    Map<String,Object> map = new HashMap<>();
    try {
      managementService.unlockUser(username,principal.getName(),reason);
    }catch (Exception error){
      logger.error(error.toString());
      map.put("msg","fail to lock user");
      RestfulResponseHelper.writeToResponse(response,401,map);
      return;
    }
    map.put("msg","OK");
    RestfulResponseHelper.writeToResponse(response,200,map);
  }

  @DeleteMapping("/admin/user")
  public void lockUserByUsername(String username,String reason,Principal principal,HttpServletResponse response) throws IOException {
    Map<String,Object> map = new HashMap<>();
    try {
      managementService.lockUser(username,principal.getName(),reason);
    }catch (Exception error){
      System.out.println(error.toString());
      map.put("msg","fail to lock user");
      RestfulResponseHelper.writeToResponse(response,401,map);
      return;
    }
    map.put("msg","OK");
    RestfulResponseHelper.writeToResponse(response,200,map);
  }

  @GetMapping("/admin/lockUserOperation")
  public void getLockUserOperation(int currentPage,int pageSize,HttpServletResponse response) throws IOException {
    PageInfo<LockUserOperation> lockUserOperationPageInfo = managementService.getLockUserOperation(currentPage,pageSize);
    Map<String,Object> map = new HashMap<>();
    map.put("totalPages",lockUserOperationPageInfo.getPages());
    map.put("totalNumber",lockUserOperationPageInfo.getTotal());
    map.put("lockOperationList",lockUserOperationPageInfo.getList());
    RestfulResponseHelper.writeToResponse(response,200,map);
  }
}
