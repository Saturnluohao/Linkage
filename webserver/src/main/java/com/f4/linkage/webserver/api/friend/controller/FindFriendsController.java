package com.f4.linkage.webserver.api.friend.controller;

import com.f4.linkage.webserver.api.friend.model.Friend;
import com.f4.linkage.webserver.api.friend.service.SearchForNewFriendsService;
import com.f4.linkage.webserver.util.RestfulResponseHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: Linkage
 * @description: To find friends
 * @author: Zijian Zhang
 * @create: 2019/11/08
 **/
@Controller
public class FindFriendsController {
  @Autowired
  private SearchForNewFriendsService searchForNewFriendsService;
  @GetMapping("/user/newFriend")
  public void getFriendsWithSimilarName(String username,Integer currentPage, Integer pageSize,HttpServletResponse response) throws IOException {
    PageInfo<Friend> friendPageInfo = searchForNewFriendsService.findFriendByName(username,currentPage,pageSize);
    Map<String,Object> map = new HashMap<>();
    map.put("totalPages",friendPageInfo.getPages());
    map.put("totalNumber",friendPageInfo.getTotal());
    map.put("friendList",friendPageInfo.getList());
    RestfulResponseHelper.writeToResponse(response,200,map);
    //System.out.println(searchForNewFriendsService.findFriendByName("z",1,2));
  }
}
