package com.f4.linkage.webserver.api.globalUser.mapper;

import com.f4.linkage.webserver.api.globalUser.model.InitialGlobalUser;
import com.f4.linkage.webserver.api.globalUser.model.LocalGlobalRelationship;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GlobalUserMapper {
  // TODO keep the relationship as an individual table to deal with further emergence of {company}
  void storeInitialGlobalUser(InitialGlobalUser initialGlobalUser);
  void addLocalGlobalRelationship(LocalGlobalRelationship localGlobalRelationship);
  InitialGlobalUser getSomeoneGlobalUser(String username);
  List<InitialGlobalUser> searchForGlobalUser(String globalUserName);
}
