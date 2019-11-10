package com.f4.linkage.webserver.api.friend.service;

import com.f4.linkage.webserver.api.friend.mapper.AddFriendRequestMapper;
import com.f4.linkage.webserver.api.friend.model.AddFriendRequest;
import com.f4.linkage.webserver.api.friend.model.Friend;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: Linkage
 * @description: add friend request
 * @author: Zijian Zhang
 * @create: 2019/11/10
 **/
@Service
public class AddFriendService {
  @Autowired
  private AddFriendRequestMapper addFriendRequestMapper;
  public void storeAddFriendRequest(AddFriendRequest friendRequest){
    addFriendRequestMapper.storeAddFriendRequest(friendRequest);
  }
  public int getLastID(){
    return addFriendRequestMapper.getLastID();
  }
  public AddFriendRequest getRequestById(int id){
    return addFriendRequestMapper.getRequestById(id);
  }
  public void storeReplyStatus(int id,boolean accept, boolean read){
    addFriendRequestMapper.storeAcceptOrNotById(id, accept, read);
  }
  public PageInfo<AddFriendRequest> getHistoryAddFriendsRequest( String username, int currentPage,int pageSize){
    PageHelper.startPage(currentPage,pageSize);
    List<AddFriendRequest> addFriendRequests = addFriendRequestMapper.getHistoryRequest(username);
    return new PageInfo<>(addFriendRequests);
  }
  public PageInfo<AddFriendRequest> getHistoryAddFriendReply(String username,int currentPage,int pageSize){
    PageHelper.startPage(currentPage,pageSize);
    List<AddFriendRequest> addFriendRequests = addFriendRequestMapper.getHistoryReply(username);
    return new PageInfo<>(addFriendRequests);
  }
}
