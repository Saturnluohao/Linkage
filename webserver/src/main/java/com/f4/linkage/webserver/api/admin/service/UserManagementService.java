package com.f4.linkage.webserver.api.admin.service;

import com.f4.linkage.webserver.api.admin.mapper.UserManagementMapper;
import com.f4.linkage.webserver.api.admin.model.info.UserManagementInfo;
import com.f4.linkage.webserver.api.admin.model.operation.AdminOperationType;
import com.f4.linkage.webserver.api.admin.model.operation.LockMomentOperation;
import com.f4.linkage.webserver.api.admin.model.operation.LockPostOperation;
import com.f4.linkage.webserver.api.admin.model.operation.LockUserOperation;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * @program: Linkage
 * @description: To manage users
 * @author: Zijian Zhang
 * @create: 2019/11/13
 **/
@Service
public class UserManagementService {
  @Autowired
  private UserManagementMapper userManagementMapper;
  public PageInfo<UserManagementInfo> searchForUserByUserName(String username, int currentPage, int pageSize){
    PageHelper.startPage(currentPage,pageSize);
    List<UserManagementInfo> userManagementInfos = userManagementMapper.getUserInfoByUsername("%"+username+"%");
    return new PageInfo<>(userManagementInfos);
  }
  @Transactional
  public void lockUser(String username,String adminName, String reason) throws SQLException {
    userManagementMapper.lockUserByUsername(username);
    LockUserOperation operation = new LockUserOperation();
    operation.setAdminName(adminName);
    operation.setOperationType(AdminOperationType.lockUser);
    operation.setReason(reason);
    operation.setUsername(username);
    userManagementMapper.addRecordForLockUser(operation);
  }
  @Transactional
  public void unlockUser(String username,String adminName, String reason) throws SQLException {
    userManagementMapper.unlockUserByUsername(username);
    LockUserOperation operation = new LockUserOperation();
    operation.setUsername(username);
    operation.setReason(reason);
    operation.setOperationType(AdminOperationType.unlockUser);
    operation.setAdminName(adminName);
    userManagementMapper.addRecordForLockUser(operation);
  }

  public PageInfo<LockUserOperation> getLockUserOperation(int currentPage,int pageSize){
    PageHelper.startPage(currentPage,pageSize);
    List<LockUserOperation> lockUserOperations = userManagementMapper.getLockUserOperation();
    return new PageInfo<>(lockUserOperations);
  }


  @Transactional
  public void lockPost(Integer postId,String adminName,String reason) throws SQLException {
    // todo lock the post
    LockPostOperation lockPostOperation = new LockPostOperation();
    lockPostOperation.setAdminName(adminName);
    lockPostOperation.setOperationType(AdminOperationType.lockPost);
    lockPostOperation.setReason(reason);
    lockPostOperation.setPostId(postId);
    userManagementMapper.addRecordForLockPost(lockPostOperation);
  }

  @Transactional
  public void unlockPost(Integer postId,String adminName,String reason) throws SQLException {
    // todo unlock the post
    LockPostOperation lockPostOperation = new LockPostOperation();
    lockPostOperation.setAdminName(adminName);
    lockPostOperation.setOperationType(AdminOperationType.unlockPost);
    lockPostOperation.setReason(reason);
    lockPostOperation.setPostId(postId);
    userManagementMapper.addRecordForLockPost(lockPostOperation);
  }
  @Transactional
  public void lockMoment(Integer momentId,String adminName,String reason) throws SQLException {
    // todo lock the moment
    LockMomentOperation operation = new LockMomentOperation();
    operation.setAdminName(adminName);
    operation.setMomentId(momentId);
    operation.setOperationType(AdminOperationType.lockMoment);
    operation.setReason(reason);
    userManagementMapper.addRecordForLockMoment(operation);
  }
  @Transactional
  public void unlockMoment(Integer momentId,String adminName,String reason) throws SQLException {
    // todo unlock the moment
    LockMomentOperation operation = new LockMomentOperation();
    operation.setAdminName(adminName);
    operation.setMomentId(momentId);
    operation.setOperationType(AdminOperationType.unlockMoment);
    operation.setReason(reason);
    userManagementMapper.addRecordForLockMoment(operation);
  }



}
