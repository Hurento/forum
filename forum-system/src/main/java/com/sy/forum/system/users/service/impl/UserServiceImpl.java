package com.sy.forum.system.users.service.impl;

import com.sy.forum.system.users.service.UserService;
import com.sy.forum.generic.GenericDao;
import com.sy.forum.exceptions.UnitedException;
import com.sy.forum.generic.GenericServiceImpl;
import com.sy.forum.system.users.dao.UserDao;
import com.sy.forum.system.users.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author SY
 * @ClassName forum-parent
 * @Description: {说明}
 * @Date 2017-04-24 21:19
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<UserInfo, String> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public GenericDao<UserInfo, String> getDao() {
        return userDao;
    }

    @Override
    public int insert(UserInfo userInfo) throws UnitedException {
        return super.insert(userInfo);
    }

    @Override
    public int update(UserInfo userInfo) throws UnitedException {
        return super.update(userInfo);
    }

    @Override
    public int delete(String id) throws UnitedException {
        return super.delete(id);
    }

    @Override
    public UserInfo getUserInfoByUsernameAndPassword(UserInfo userInfo) {
        return userDao.getUserInfoByUsernameAndPassword(userInfo);
    }
}
