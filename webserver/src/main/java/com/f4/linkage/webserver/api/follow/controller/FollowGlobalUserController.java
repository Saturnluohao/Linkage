package com.f4.linkage.webserver.api.follow.controller;

import com.f4.linkage.webserver.api.follow.model.FollowRelationship;
import com.f4.linkage.webserver.api.follow.service.FollowerService;
import com.f4.linkage.webserver.api.globalUser.model.InitialGlobalUser;
import com.f4.linkage.webserver.util.RestfulResponseHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: Linkage
 * @description: The controller for handling local user following global users
 * @author: Zijian Zhang
 * @create: 2019/11/11
 **/
@Controller
public class FollowGlobalUserController {
  private Logger logger = LoggerFactory.getLogger(FollowGlobalUserController.class);
  @Autowired
  private FollowerService followerService;
  @GetMapping("/user/follow")
  void getGlobalUsersIFollow(Principal principal, int currentPage, int pageSize, HttpServletResponse response) throws IOException {
    PageInfo<InitialGlobalUser> initialGlobalUserPageInfo = followerService.getGlobalUsersIFollowed(principal.getName(),currentPage,pageSize);
    Map<String,Object> map = new HashMap<>();
    map.put("totalNumber",initialGlobalUserPageInfo.getTotal());
    map.put("totalPages",initialGlobalUserPageInfo.getPages());
    map.put("followedList",initialGlobalUserPageInfo.getList());
    RestfulResponseHelper.writeToResponse(response,200,map);
  }
  @PostMapping("/user/follow")
  void addFollowGlobalUSer(Principal principal, String globalUserName, HttpServletResponse response) throws IOException {
    FollowRelationship relationship = new FollowRelationship();
    List<String> initialGlobalUserList = followerService.getAllGlobalAccountIFollowed(principal.getName());
    Map<String,Object> map = new HashMap<>();
    if(initialGlobalUserList.contains(globalUserName)){
      map.put("msg","you have already followed this one");
      RestfulResponseHelper.writeToResponse(response,401,map);
    }else {
      relationship.setGlobalUsername(globalUserName);
      relationship.setLocalUsername(principal.getName());
      try {
        followerService.addFollow(relationship);
      }catch (Exception e){
        map.put("msg","global user not found!");
        RestfulResponseHelper.writeToResponse(response,401,map);
        return;
      }
      map.put("msg","ok");
      RestfulResponseHelper.writeToResponse(response,200,map);
    }
  }
  @DeleteMapping("/user/follow")
  void deleteGlobalUserIFollow(Principal principal,String targetGlobalUserName,HttpServletResponse response) throws IOException {
    Map<String,Object> map = new HashMap<>();
    try {
      followerService.deleteSubscription(principal.getName(),targetGlobalUserName);
    }catch (Exception e){
      logger.error(e.toString());
      map.put("msg","delete subscription fail");
      RestfulResponseHelper.writeToResponse(response,401,map);
      return;
    }
    map.put("msg","ok");
    RestfulResponseHelper.writeToResponse(response,200,map);
  }
}
