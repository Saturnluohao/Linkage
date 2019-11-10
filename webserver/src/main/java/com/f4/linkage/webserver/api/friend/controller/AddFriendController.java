package com.f4.linkage.webserver.api.friend.controller;

import com.f4.linkage.webserver.api.friend.model.AddFriendRequest;
import com.f4.linkage.webserver.api.friend.service.AddFriendService;
import com.f4.linkage.webserver.api.friend.service.FriendService;
import com.f4.linkage.webserver.config.OnlineUserHub;
import com.f4.linkage.webserver.util.RestfulResponseHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: Linkage
 * @description: The controller dealing with adding friends
 * @author: Zijian Zhang
 * @create: 2019/11/10
 **/
@Controller
public class AddFriendController {
  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;
  @Autowired
  private OnlineUserHub onlineUserHub;
  @Autowired
  private AddFriendService addFriendService;
  @Autowired
  private FriendService friendService;
  @MessageMapping("/friend/add")
  public void addFriend(Principal principal, AddFriendRequest friendRequest){
    String name = principal.getName();
    friendRequest.setUsername(name);
    friendRequest.setReplyStatus(null);
    boolean targetIsOnline = onlineUserHub.findUserByName(friendRequest.getTargetName());
    if(targetIsOnline){
      friendRequest.setReadStatus(true);
    }else {
      friendRequest.setReadStatus(false);
    }
    addFriendService.storeAddFriendRequest(friendRequest);
    int id = addFriendService.getLastID();
    friendRequest.setId(id);
    simpMessagingTemplate.convertAndSendToUser(friendRequest.getTargetName(),"/queue/friend/request",friendRequest);

  }

  @MessageMapping("/friend/check")
  public void decideAddFriend(Principal principal,AddFriendRequest friendRequest){
    AddFriendRequest dbRequest = addFriendService.getRequestById(friendRequest.getId());
    assert dbRequest.getTargetName().equals(principal.getName());
    assert friendRequest.getAcceptStatus() != null;
    boolean userIsOnline = onlineUserHub.findUserByName(friendRequest.getUsername());
    if(userIsOnline){
      friendRequest.setReplyStatus(true);
      simpMessagingTemplate.convertAndSendToUser(friendRequest.getUsername(),"/queue/friend/reply",friendRequest);
    }else {
      friendRequest.setReplyStatus(false);
    }
    if(friendRequest.getAcceptStatus()){
      friendService.addFriend(friendRequest.getTargetName(),friendRequest.getUsername());
    }
    addFriendService.storeReplyStatus(friendRequest.getId(),friendRequest.getAcceptStatus(),friendRequest.getReplyStatus());
  }

  @GetMapping("/user/friend/request")
  public void getHistoryRequest(Principal principal, int currentPage, int pageSize, HttpServletResponse response) throws IOException {
     /**
       * @description:  get the add friend info where i am the {{target}}
       *
       * @return : void
       **/
    PageInfo<AddFriendRequest> addFriendRequestPageInfo = addFriendService.getHistoryAddFriendsRequest(principal.getName(),currentPage, pageSize);
    Map<String, Object> map = new HashMap<>();
    map.put("totalPages",addFriendRequestPageInfo.getPages());
    map.put("totalNumber",addFriendRequestPageInfo.getTotal());
    map.put("RequestList",addFriendRequestPageInfo.getList());
    RestfulResponseHelper.writeToResponse(response,200,map);
  }

  @GetMapping("/user/friend/reply")
  public void getHistoryReply(Principal principal,int currentPage,int pageSize,HttpServletResponse response) throws IOException {
     /**
       * @description: get the add friend info where i am the {{sender}}
       *
       * @return : void
       **/
    PageInfo<AddFriendRequest> addFriendReplyPageInfo = addFriendService.getHistoryAddFriendReply(principal.getName(),currentPage, pageSize);
    Map<String, Object> map = new HashMap<>();
    map.put("totalPages",addFriendReplyPageInfo.getPages());
    map.put("totalNumber",addFriendReplyPageInfo.getTotal());
    map.put("ReplyList",addFriendReplyPageInfo.getList());
    RestfulResponseHelper.writeToResponse(response,200,map);
  }
}
