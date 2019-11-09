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
 * @description: Find friends with similar name
 * @author: Zijian Zhang
 * @create: 2019/11/08
 **/
@Service
public class SearchForNewFriendsService {
  @Autowired
  private FriendMapper friendMapper;
  public PageInfo<Friend> findFriendByName(String username, int currentPage, int pageSize){
    PageHelper.startPage(currentPage,pageSize);
    List<Friend> friends = friendMapper.SearchFriendByName("%" + username + "%");
    //System.out.println(friendPageInfo.isHasNextPage());
    return new PageInfo<>(friends);
  }

}
