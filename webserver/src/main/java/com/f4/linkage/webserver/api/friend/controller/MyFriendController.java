package com.f4.linkage.webserver.api.friend.controller;

import com.f4.linkage.webserver.api.friend.model.Friend;
import com.f4.linkage.webserver.api.friend.service.FriendService;
import com.f4.linkage.webserver.util.RestfulResponseHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: Linkage
 * @description: CRUD my friend
 * @author: Zijian Zhang
 * @create: 2019/11/08
 **/
@RestController
public class MyFriendController {
  private Logger logger = LoggerFactory.getLogger(MyFriendController.class);
  @Autowired
  private FriendService friendService;
  @GetMapping("/user/myFriend")
  public void getMyFriend(Principal principal, Integer currentPage, Integer pageSize, HttpServletResponse response) throws IOException {
    PageInfo<Friend> friendPageInfo = friendService.getMyFriendsByPages(principal.getName(),currentPage,pageSize);
    Map<String,Object> map = new HashMap<>();
    map.put("totalPages",friendPageInfo.getPages());
    map.put("totalNumber",friendPageInfo.getTotal());
    map.put("friendList",friendPageInfo.getList());
    RestfulResponseHelper.writeToResponse(response,200,map);
  }
  @DeleteMapping("/user/myFriend")
  public void deleteMyFriend(Principal principal,String friendName,HttpServletResponse response) throws IOException {
    Map<String,Object> map = new HashMap<>();
    try {
      friendService.deleteFriend(principal.getName(),friendName);
    }catch (Exception e){
      map.put("msg","delete friend fail");
      RestfulResponseHelper.writeToResponse(response,401,map);
      logger.error(e.toString());
    }
    map.put("msg","ok");
    RestfulResponseHelper.writeToResponse(response,200,map);
  }
  @GetMapping("/user/myFriend/info")
  public void getMyFriendInfo(Principal principal, String friendName,HttpServletResponse response) throws IOException {
    List<String> myFriendNameList = friendService.getAllMyFriends(principal.getName());
    Map<String,Object> map = new HashMap<>();
    if(myFriendNameList.contains(friendName)){
      Friend friend = friendService.getMyFriendDetailInfo(friendName);
      map.put("info",friend);
      map.put("msg","OK");
      RestfulResponseHelper.writeToResponse(response,200,map);
      return;
    }
    map.put("msg","Not your friend");
    RestfulResponseHelper.writeToResponse(response,401,map);
  }
}
