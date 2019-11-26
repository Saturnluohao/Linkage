package com.f4.linkage.webserver.api.friend.mapper;

import com.f4.linkage.webserver.api.friend.model.AddFriendRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AddFriendRequestMapper {
  void storeAddFriendRequest(AddFriendRequest addFriendRequest);
  Integer getLastID();
  AddFriendRequest getRequestById(int id);
  void storeAcceptOrNotById(int id, boolean accept,boolean read);
  List<AddFriendRequest> getHistoryRequest(String username); // target is me
  List<AddFriendRequest> getHistoryReply(String username);  // sender is me
  List<AddFriendRequest> getUnreadRequest(String username); // target is me
  List<AddFriendRequest> getUnreadReply(String username); // sender is me
  void setReadStatusReadById(Integer id);
  void setReplyStatusReadById(Integer id);
}
