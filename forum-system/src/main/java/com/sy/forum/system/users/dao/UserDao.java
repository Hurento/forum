package com.sy.forum.system.users.dao;

import com.sy.forum.generic.GenericDao;
import com.sy.forum.system.users.model.UserInfo;
import org.springframework.stereotype.Repository;


/**
 * @Author SY
 * @ClassName UserDao
 * @Description: 用户数据层接口
 * @Date 2017-04-24 10:42
 */
@Repository
public interface UserDao extends GenericDao<UserInfo, String> {

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
