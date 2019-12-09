package com.f4.linkage.webserver.api.login.model;

import com.f4.linkage.webserver.api.login.model.unread.UnreadAddFriendReply;
import com.f4.linkage.webserver.api.login.model.unread.UnreadAddFriendRequest;
import com.f4.linkage.webserver.api.login.model.unread.UnreadMessage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @program: Linkage
 * @description: LoginUserInfo
 * @author: Zijian Zhang
 * @create: 2019/10/26
 **/
@Data
public class LoginUserInfo implements UserDetails, Serializable {
  private Integer id;
  private String userName;
  // private String phoneNumber;
  private UnreadMessage unreadMessage;
  private UnreadAddFriendRequest unreadAddFriendRequest;
  private UnreadAddFriendReply unreadAddFriendReply;
  private boolean isAdmin;

  @JsonIgnore
  private String password;
  @JsonIgnore
  private boolean enabled;
  @JsonIgnore
  private boolean locked;
  @JsonIgnore
  private List<Role> roles;


  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    for (Role role :
      roles) {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    }
    return authorities;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return !locked;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isEnabled(){
    return enabled;
  }

  @JsonIgnore
  @Override
  public String getPassword(){
    return password;
  }
}
