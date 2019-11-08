package com.f4.linkage.webserver.api.friend.controller;

import com.f4.linkage.webserver.api.friend.service.SearchForNewFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

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
  @GetMapping("/friend")
  public void getFriendsWithSimilarName(String username,HttpServletResponse response){
   System.out.println(searchForNewFriendsService.findFriendByName("z",1,2));

  }
}
