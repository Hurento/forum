package com.sy.forum.system.users.model;

import java.io.Serializable;

/**
 * @Author SY
 * @ClassName UserInfo
 * @Description: 用户
 * @Date 2017-04-20 11:40
 */
public class UserInfo implements Serializable {
    private String userId;// 用户id
    private String userName;// 用户姓名
    private String loginName;// 登录名称
    private String loginPassword;// 登录密码
    private String mobilePhone;// 移动电话
    private String emailAdress;// 邮箱地址
    private String vcCardNo;// 身份证号码
    private String cureentStatus;// 用户当前状态
    private String errorCount;// 登录错误次数
    private String lockStatus;// 用户锁定状态
    private String currentLoginTime;// 当前登录时间
    private String curentLoginAdress;// 当前登录地址
    private String curentLoginIP;// 当前登录ip
    private String userType;//用户类型（0：前端、1：后端）

    public UserInfo() {
    }

    public UserInfo(String loginName, String loginPassword) {
        this.loginName = loginName;
        this.loginPassword = loginPassword;
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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getVcCardNo() {
        return vcCardNo;
    }

    public void setVcCardNo(String vcCardNo) {
        this.vcCardNo = vcCardNo;
    }

    public String getCureentStatus() {
        return cureentStatus;
    }

    public void setCureentStatus(String cureentStatus) {
        this.cureentStatus = cureentStatus;
    }

    public String getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(String errorCount) {
        this.errorCount = errorCount;
    }

    public String getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(String lockStatus) {
        this.lockStatus = lockStatus;
    }

    public String getCurrentLoginTime() {
        return currentLoginTime;
    }

    public void setCurrentLoginTime(String currentLoginTime) {
        this.currentLoginTime = currentLoginTime;
    }

    public String getCurentLoginAdress() {
        return curentLoginAdress;
    }

    public void setCurentLoginAdress(String curentLoginAdress) {
        this.curentLoginAdress = curentLoginAdress;
    }

    public String getCurentLoginIP() {
        return curentLoginIP;
    }

    public void setCurentLoginIP(String curentLoginIP) {
        this.curentLoginIP = curentLoginIP;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
