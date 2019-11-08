package com.f4.linkage.webserver.api.friend.service;

import com.f4.linkage.webserver.api.friend.mapper.FriendMapper;
import com.f4.linkage.webserver.api.friend.model.Friend;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: Linkage
 * @description: Get user's friends.
 * @author: Zijian Zhang
 * @create: 2019/11/08
 **/
@Service
public class GetMyFriendsService {
  @Autowired
  private FriendMapper friendMapper;
  public PageInfo<Friend> getMyFriends(String username,int currentPage,int pageSize){
    PageHelper.startPage(currentPage,pageSize);
    List<Friend> myFriends = friendMapper.GetMyFriends(username);
    return new PageInfo<>(myFriends);
  }
}
