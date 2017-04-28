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
}
