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
public class FriendService {
  @Autowired
  private FriendMapper friendMapper;
  public List<String> getAllMyFriends(String username){
    return friendMapper.getAllMyFriends(username);
  }

  public PageInfo<Friend> getMyFriendsByPages(String username, int currentPage, int pageSize){
    PageHelper.startPage(currentPage,pageSize);
    List<Friend> myFriends = friendMapper.GetMyFriends(username);
    return new PageInfo<>(myFriends);
  }
  public PageInfo<Friend> findFriendByName(String username, int currentPage, int pageSize){
    PageHelper.startPage(currentPage,pageSize);
    List<Friend> friends = friendMapper.SearchFriendByName("%" + username + "%");
    //System.out.println(friendPageInfo.isHasNextPage());
    return new PageInfo<>(friends);
  }
  public Friend getMyFriendDetailInfo(String friendName){return friendMapper.getMyFriendByName(friendName);}
  public void addFriend(String userName, String targetName){
    friendMapper.addFriend(userName,targetName);
  }
  public void deleteFriend(String userName,String targetName){friendMapper.deleteFriend(userName,targetName);}
}
