package com.f4.linkage.webserver.api.follow.service;

import com.f4.linkage.webserver.api.follow.mapper.FollowMapper;
import com.f4.linkage.webserver.api.follow.model.FollowRelationship;
import com.f4.linkage.webserver.api.globalUser.model.InitialGlobalUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: Linkage
 * @description: To add some followers or get all followers
 * @author: Zijian Zhang
 * @create: 2019/11/11
 **/
@Service
public class FollowerService {
  @Autowired
  private FollowMapper followMapper;
  public void addFollow(FollowRelationship relationship){
    System.out.println("Service:");
    followMapper.addFollowRelationship(relationship);
  }
  public List<String> getAllFollowers(String globalUserName){
    return followMapper.getAllFollowerName(globalUserName);
  }
  public PageInfo<InitialGlobalUser> getGlobalUsersIFollowed(String username,int currentPage,int pageSize){
    PageHelper.startPage(currentPage,pageSize);
    List<InitialGlobalUser> initialGlobalUserList = followMapper.getGlobalUserIFollow(username);
    return new PageInfo<>(initialGlobalUserList);
  }
  public List<String> getAllGlobalAccountIFollowed(String userName){
    return followMapper.getGlobalUserNameIFollowed(userName);
  }
}
