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
}
