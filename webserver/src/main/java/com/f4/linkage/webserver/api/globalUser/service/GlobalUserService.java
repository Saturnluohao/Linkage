package com.f4.linkage.webserver.api.globalUser.service;

import com.f4.linkage.webserver.api.follow.mapper.FollowMapper;
import com.f4.linkage.webserver.api.follow.model.FollowerInfo;
import com.f4.linkage.webserver.api.globalUser.mapper.GlobalUserMapper;
import com.f4.linkage.webserver.api.globalUser.model.InitialGlobalUser;
import com.f4.linkage.webserver.api.globalUser.model.LocalGlobalRelationship;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: Linkage
 * @description: Service in order to create and modify global users
 * @author: Zijian Zhang
 * @create: 2019/11/11
 **/
@Service
public class GlobalUserService {
  @Autowired
  private GlobalUserMapper globalUserMapper;
  @Autowired
  private FollowMapper followMapper;
  public void createNewGlobalUser(InitialGlobalUser globalUser){
    globalUserMapper.storeInitialGlobalUser(globalUser);
  }
//  public void addNewGlobalUserMapper(LocalGlobalRelationship relationship){
//    globalUserMapper.addLocalGlobalRelationship(relationship);
//  }
  public InitialGlobalUser getGlobalAccountInfoByLocalUserName(String username){
    return globalUserMapper.getSomeoneGlobalUser(username);
  }
  public PageInfo<InitialGlobalUser> searchForGlobalUserWhoseNameContains(String globalUserName,int currentPage,int pageSize){

    PageHelper.startPage(currentPage,pageSize);
    List<InitialGlobalUser> initialGlobalUserList = globalUserMapper.searchForGlobalUser("%"+globalUserName+"%");
    return new PageInfo<>(initialGlobalUserList);
  }
  public int getFollowerNumbers(String globalUserName){
    return followMapper.getAllFollowerNumber(globalUserName);
  }
  public void changeDescription(String globalUserName, String description){globalUserMapper.changeGlobalUserDescription(globalUserName,description);}
  public InitialGlobalUser getGlobalAccountByGlobalUserName(String globalUserName){return globalUserMapper.getGlobalUserByGlobalUserName(globalUserName);}
  public PageInfo<FollowerInfo> getMyFollowerInfoByPage(String globalUserName,int currentPage,int pageSize){
    PageHelper.startPage(currentPage,pageSize);
    List<FollowerInfo> followerInfos = followMapper.getMyFollowers(globalUserName);
    return new PageInfo<>(followerInfos);
  }
}
