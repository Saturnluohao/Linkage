package com.f4.linkage.webserver.api.login.model.unread;

import com.f4.linkage.webserver.api.friend.model.AddFriendRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: Linkage
 * @description: Info about add friend {{request}} the target is me
 * @author: Zijian Zhang
 * @create: 2019/11/10
 **/
@Data
public class UnreadAddFriendRequest implements Serializable {
  private int count;
  private List<AddFriendRequest> unreadList;
}
