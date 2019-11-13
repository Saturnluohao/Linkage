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
  List<String> getAllMyFriends(String name);
  void addFriend(String name_1, String name_2);
  void deleteFriend(String name_1,String name_2);
}
