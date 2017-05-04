package com.sy.forum.system.users.service.impl;

import com.sy.forum.exceptions.UnitedException;
import com.sy.forum.generic.GenericDao;
import com.sy.forum.generic.GenericServiceImpl;
import com.sy.forum.system.users.dao.UserDao;
import com.sy.forum.system.users.model.UserInfo;
import com.sy.forum.system.users.service.UserService;
import com.sy.forum.utils.SecurityUtil;
import com.sy.forum.utils.Utils;
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
        try {
            // 密码不为空时
            if(!Utils.isEmpty(userInfo.getLoginPassword())) {
                String password = SecurityUtil.base64MultiDecrypt(userInfo.getLoginPassword());
                String userType = password.substring(password.indexOf("-") + 1, password.length());//用户类型
                userType = SecurityUtil.base64MultiDecrypt(userType);//三层解密
                password = password.substring(0, password.indexOf("-"));// 密码
                userInfo.setLoginPassword(password);
                userInfo.setUserType(userType);// 用以区分前端后端
            } else {
                String userType = SecurityUtil.base64MultiDecrypt(userInfo.getUserType());
                userType = userType.substring(userType.indexOf("-") + 1, userType.length());//用户类型
                userType = SecurityUtil.base64MultiDecrypt(userType);//三层解密
                userInfo.setUserType(userType);// 用以区分前端后端
            }
        } catch (Exception e) {
        }
        return userDao.getUserInfoByUsernameAndPassword(userInfo);
    }

    @Override
    public void insertLoginUserAddressInfo(UserInfo userInfo) {
        userDao.insertLoginUserAddressInfo(userInfo);
    }

    @Override
    public void updateUserLoginInfoByUserId(String userId) {
        userDao.updateUserLoginInfoByUserId(userId);
    }

    @Override
    public void updateUserLockStatusByUserId(String userId) {
        userDao.updateUserLockStatusByUserId(userId);
    }

    @Override
    public void updateUserErrorCountByUserId(String errorCount, String userId) {
        userDao.updateUserErrorCountByUserId(errorCount, userId);
    }
}
