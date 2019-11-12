package com.f4.linkage.webserver.api.login.service;

import com.f4.linkage.webserver.api.chat.mapper.MessageMapper;
import com.f4.linkage.webserver.api.friend.mapper.AddFriendRequestMapper;
import com.f4.linkage.webserver.api.login.mapper.UserMapper;
import com.f4.linkage.webserver.api.login.model.LoginUserInfo;
import com.f4.linkage.webserver.api.login.model.UserSelfInfo;
import com.f4.linkage.webserver.api.login.model.unread.UnreadAddFriendReply;
import com.f4.linkage.webserver.api.login.model.unread.UnreadAddFriendRequest;
import com.f4.linkage.webserver.api.login.model.unread.UnreadMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @program: Linkage
 * @description: LoginUserInfo CRUD
 * @author: Zijian Zhang
 * @create: 2019/10/26
 **/
@Service
public class UserService implements UserDetailsService {
  @Autowired
  private UserMapper userMapper;
  @Autowired
  private MessageMapper messageMapper;
  @Autowired
  private AddFriendRequestMapper addFriendRequestMapper;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    LoginUserInfo loginUserInfo = userMapper.loadUserByUserName(s);
    if(loginUserInfo == null){
      throw new UsernameNotFoundException("The account is not registered!");
    }
    loginUserInfo.setRoles(userMapper.getUserRoleByUserId(loginUserInfo.getId()));

    UnreadMessage unreadMessage = new UnreadMessage();
    unreadMessage.setUnreadList(messageMapper.getUnreadMessage(s));
    unreadMessage.setCount(unreadMessage.getUnreadList().size());
    loginUserInfo.setUnreadMessage(unreadMessage);

    UnreadAddFriendReply unreadAddFriendReply = new UnreadAddFriendReply();
    unreadAddFriendReply.setUnreadList(addFriendRequestMapper.getUnreadReply(s));
    unreadAddFriendReply.setCount(unreadAddFriendReply.getUnreadList().size());
    loginUserInfo.setUnreadAddFriendReply(unreadAddFriendReply);

    UnreadAddFriendRequest unreadAddFriendRequest = new UnreadAddFriendRequest();
    unreadAddFriendRequest.setUnreadList(addFriendRequestMapper.getUnreadRequest(s));
    unreadAddFriendRequest.setCount(unreadAddFriendRequest.getUnreadList().size());
    loginUserInfo.setUnreadAddFriendRequest(unreadAddFriendRequest);
    userMapper.changeTimeStamp(s);
    return loginUserInfo;
  }

  public UserSelfInfo getUserInfoByHimself(String username){
    return userMapper.getUserSelfInfoByUsername(username);
  }
  public void updateUserInfo(UserSelfInfo selfInfo){
    userMapper.updateSelfInfo(selfInfo);
  }

}
