package com.f4.linkage.webserver.api.friend.controller;

import com.f4.linkage.webserver.api.friend.model.Friend;
import com.f4.linkage.webserver.api.friend.service.GetMyFriendsService;
import com.f4.linkage.webserver.util.RestfulResponseHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: Linkage
 * @description: CRUD my friend
 * @author: Zijian Zhang
 * @create: 2019/11/08
 **/
@RestController
public class MyFriendController {
  @Autowired
  private GetMyFriendsService getMyFriendsService;
  @GetMapping("/user/myFriend")
  void getMyFriend(Principal principal, Integer currentPage, Integer pageSize, HttpServletResponse response) throws IOException {
    PageInfo<Friend> friendPageInfo = getMyFriendsService.getMyFriends(principal.getName(),currentPage,pageSize);
    Map<String,Object> map = new HashMap<>();
    map.put("totalPages",friendPageInfo.getPages());
    map.put("totalNumber",friendPageInfo.getTotal());
    map.put("friendList",friendPageInfo.getList());
    RestfulResponseHelper.writeToResponse(response,200,map);
  }
}