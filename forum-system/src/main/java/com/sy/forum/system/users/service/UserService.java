package com.sy.forum.system.users.service;

import com.sy.forum.generic.GenericService;
import com.sy.forum.system.users.model.UserInfo;

/**
 * @Author SY
 * @ClassName UserService
 * @Description: 用户业务接口
 * @Date 2017-04-24 10:38
 */
public interface UserService extends GenericService<UserInfo, String> {
    /**
     * 根据用户名与密码查询用户
     * @param userInfo
     * @return
     */
    UserInfo getUserInfoByUsernameAndPassword(UserInfo userInfo);

    /**
     * 记录登录用户地理信息
     * @param userInfo
     */
    void insertLoginUserAddressInfo(UserInfo userInfo);

    /**
     * 登录成功修改错误次数与锁定状态
     * @param userId
     */
    void updateUserLoginInfoByUserId(String userId);

    /**
     * 锁定用户
     * @param userId
     */
    void updateUserLockStatusByUserId(String userId);

    /**
     * 记录登录错误次数
     * @param errorCount
     * @param userId
     */
    void updateUserErrorCountByUserId(String errorCount, String userId);
}
