package com.sy.forum.system.users.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;

/**
 * @Author SY
 * @ClassName UserInfo
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Date 2017-04-20 11:40
 */
public class UserInfo implements Serializable {
    private String userId;
    private String userName;
    private String userPass;

    public UserInfo() {
    }

    public UserInfo(String userName, String userPass) {
        this.userName = userName;
        this.userPass = userPass;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}
