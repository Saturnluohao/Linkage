package com.f4.linkage.webserver.api.friend.controller;

import com.f4.linkage.webserver.api.friend.model.AddFriendRequest;
import com.f4.linkage.webserver.api.friend.service.AddFriendService;
import com.f4.linkage.webserver.api.friend.service.FriendService;
import com.f4.linkage.webserver.config.OnlineUserHub;
import com.f4.linkage.webserver.util.RestfulResponseHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
  private Logger logger = LoggerFactory.getLogger(AddFriendController.class);
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

  @MessageMapping("/friend/add/confirm")
  public void makeRequestAlreadyRead(Principal principal,AddFriendRequest friendRequest){
    if(!friendRequest.getTargetName().equals(principal.getName())) return;
    logger.info("No."+friendRequest.getId() + " request is read.");
    addFriendService.setReadStatusRead(friendRequest.getId());
  }

  @MessageMapping("/friend/check")
  public void decideAddFriend(Principal principal,AddFriendRequest friendRequest){
    System.out.println(friendRequest);
    AddFriendRequest dbRequest = addFriendService.getRequestById(friendRequest.getId());
    assert dbRequest.getTargetName().equals(principal.getName());
    assert friendRequest.getAcceptStatus() != null;
    boolean userIsOnline = onlineUserHub.findUserByName(dbRequest.getUsername());
    dbRequest.setAcceptStatus(friendRequest.getAcceptStatus());
    if(userIsOnline){
      dbRequest.setReplyStatus(true);
    }else {
      dbRequest.setReplyStatus(false);
    }
    if(dbRequest.getAcceptStatus()){
      friendService.addFriend(dbRequest.getTargetName(),dbRequest.getUsername());
    }
    addFriendService.storeReplyStatus(dbRequest.getId(),dbRequest.getAcceptStatus(),dbRequest.getReplyStatus());
    simpMessagingTemplate.convertAndSendToUser(dbRequest.getUsername(),"/queue/friend/reply",dbRequest);
  }

  @MessageMapping("/friend/check/confirm")
  public void makeReplyStatusAlreadyRead(Principal principal,AddFriendRequest friendRequest){
    if(!friendRequest.getUsername().equals(principal.getName())) return;
    logger.info("No."+friendRequest.getId()+" reply is read.");
    addFriendService.setReplyStatusRead(friendRequest.getId());
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
