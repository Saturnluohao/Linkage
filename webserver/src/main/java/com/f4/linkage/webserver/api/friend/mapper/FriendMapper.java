package com.f4.linkage.webserver.api.friend.mapper;

import com.f4.linkage.webserver.api.friend.model.Friend;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FriendMapper {
  List<Friend> SearchFriendByName(String name);
  List<Friend> GetMyFriends(String myName);
}
