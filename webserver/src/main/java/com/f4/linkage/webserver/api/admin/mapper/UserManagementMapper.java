package com.f4.linkage.webserver.api.admin.mapper;

import com.f4.linkage.webserver.api.admin.model.operation.LockMomentOperation;
import com.f4.linkage.webserver.api.admin.model.operation.LockPostOperation;
import com.f4.linkage.webserver.api.admin.model.info.UserManagementInfo;
import com.f4.linkage.webserver.api.admin.model.operation.LockUserOperation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Mapper
@Repository
public interface UserManagementMapper {
  List<UserManagementInfo> getUserInfoByUsername(String username);
  void lockUserByUsername(String username);
  void unlockUserByUsername(String username);
  List<LockUserOperation> getLockUserOperation();

  // TODO add lock post, lock moment operation here { 6 functions }

  void addRecordForLockUser(LockUserOperation operation) throws SQLException;
  void addRecordForLockPost(LockPostOperation operation) throws SQLException ;
  void addRecordForLockMoment(LockMomentOperation operation) throws SQLException;
}
