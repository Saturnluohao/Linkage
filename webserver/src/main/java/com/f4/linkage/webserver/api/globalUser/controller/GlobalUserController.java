package com.f4.linkage.webserver.api.globalUser.controller;

import com.f4.linkage.webserver.api.follow.model.FollowerInfo;
import com.f4.linkage.webserver.api.globalUser.model.InitialGlobalUser;
import com.f4.linkage.webserver.api.globalUser.service.GlobalUserService;
import com.f4.linkage.webserver.util.RestfulResponseHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: Linkage
 * @description: The controller of global user.
 * @author: Zijian Zhang
 * @create: 2019/11/11
 **/
@Controller
public class GlobalUserController {
  private Logger logger = LoggerFactory.getLogger(GlobalUserService.class);
  @Autowired
  private GlobalUserService globalUserService;
  @GetMapping("/user/globalAccount/me")
  // find out user's global account
  void findOutWhetherHasGlobalAccount(Principal principal, HttpServletResponse response) throws IOException {
    String username = principal.getName();
    InitialGlobalUser globalUser = globalUserService.getGlobalAccountInfoByLocalUserName(username);
    Map<String,Object> map = new HashMap<>();
    if(globalUser==null){
      map.put("globalUser",null);
      RestfulResponseHelper.writeToResponse(response,401,map);
    }else {
      globalUser.setFollowerNumber(globalUserService.getFollowerNumbers(globalUser.getUsername()));
      map.put("globalUser",globalUser);
      RestfulResponseHelper.writeToResponse(response,200,map);
    }
  }
  @PostMapping("/user/globalAccount/me")
  // create global account
  void createANewGlobalAccount(Principal principal, @RequestBody InitialGlobalUser initialGlobalUser,HttpServletResponse response) throws IOException {
    InitialGlobalUser globalUser = globalUserService.getGlobalAccountInfoByLocalUserName(principal.getName());
    Map<String,Object> map = new HashMap<>();
    if(globalUser!=null){
      map.put("msg","already has global account");
      RestfulResponseHelper.writeToResponse(response,401,map);
    }else {
      initialGlobalUser.setLocalUserName(principal.getName());
      try {
        globalUserService.createNewGlobalUser(initialGlobalUser);
        map.put("msg","ok");
        RestfulResponseHelper.writeToResponse(response,200,map);
      }catch (Exception e){
        logger.error(e.toString());
        map.put("msg","global name used, change another one");
        RestfulResponseHelper.writeToResponse(response,401,map);
      }
    }
  }
  @GetMapping("/user/globalAccount/all")
  // search for global account
  void searchForGlobalAccount(String globalName,int currentPage,int pageSize,HttpServletResponse response) throws IOException {
    PageInfo<InitialGlobalUser> initialGlobalUserPageInfo = globalUserService.searchForGlobalUserWhoseNameContains(globalName,currentPage,pageSize);
    Map<String,Object> map = new HashMap<>();
    map.put("totalNumber",initialGlobalUserPageInfo.getTotal());
    map.put("totalPages",initialGlobalUserPageInfo.getPages());
    map.put("globalUserList",initialGlobalUserPageInfo.getList());
    RestfulResponseHelper.writeToResponse(response,200,map);
  }

  @PutMapping("/user/globalAccount/description")
  void changeGlobalUserDescription(Principal principal,String description, HttpServletResponse response) throws IOException {
    InitialGlobalUser globalUserName = globalUserService.getGlobalAccountInfoByLocalUserName(principal.getName());
    if(globalUserName == null){
      Map<String,Object> map = new HashMap<>();
      map.put("msg","change description fail");
      RestfulResponseHelper.writeToResponse(response,401,map);
      return;
    }
    globalUserService.changeDescription(globalUserName.getUsername(),description);
    Map<String,Object> map = new HashMap<>();
    map.put("msg","OK");
    RestfulResponseHelper.writeToResponse(response,200,map);
  }
  @GetMapping("/user/globalAccount/info")
  void getGlobalAccountInfo(String globalUserName,HttpServletResponse response) throws IOException {
    Map<String,Object> map = new HashMap<>();
    InitialGlobalUser globalUser = globalUserService.getGlobalAccountByGlobalUserName(globalUserName);
    if(globalUser==null){
      map.put("msg","not found");
      RestfulResponseHelper.writeToResponse(response,401,map);
      return;
    }
    globalUser.setFollowerNumber(globalUserService.getFollowerNumbers(globalUserName));
    map.put("msg","OK");
    map.put("globalUserInfo",globalUser);
    RestfulResponseHelper.writeToResponse(response,200,map);
  }
  @GetMapping("/user/globalAccount/me/followerList")
  void getMyFollowerListByPage( Principal principal,int currentPage,int pageSize,HttpServletResponse response) throws IOException {
    InitialGlobalUser globalUser = globalUserService.getGlobalAccountInfoByLocalUserName(principal.getName());
    Map<String,Object> map = new HashMap<>();
    if(globalUser == null){
      map.put("msg","you have no global account");
      RestfulResponseHelper.writeToResponse(response,401,map);
      return;
    }
    PageInfo<FollowerInfo> followerInfoPageInfo = globalUserService.getMyFollowerInfoByPage(globalUser.getUsername(),currentPage,pageSize);
    map.put("totalNumber",followerInfoPageInfo.getTotal());
    map.put("totalPages",followerInfoPageInfo.getPages());
    map.put("followerList",followerInfoPageInfo.getList());
    RestfulResponseHelper.writeToResponse(response,200,map);
  }
}
