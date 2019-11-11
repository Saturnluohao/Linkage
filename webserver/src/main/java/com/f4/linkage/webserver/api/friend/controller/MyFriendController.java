package com.f4.linkage.webserver.api.friend.controller;

import com.f4.linkage.webserver.api.friend.model.Friend;
import com.f4.linkage.webserver.api.friend.service.FriendService;
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
  private FriendService friendService;
  @GetMapping("/user/myFriend")
  void getMyFriend(Principal principal, Integer currentPage, Integer pageSize, HttpServletResponse response) throws IOException {
    PageInfo<Friend> friendPageInfo = friendService.getMyFriendsByPages(principal.getName(),currentPage,pageSize);
    Map<String,Object> map = new HashMap<>();
    map.put("totalPages",friendPageInfo.getPages());
    map.put("totalNumber",friendPageInfo.getTotal());
    map.put("friendList",friendPageInfo.getList());
    RestfulResponseHelper.writeToResponse(response,200,map);
  }
}
