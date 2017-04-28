package com.sy.forum.system.users.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;

/**
 * @Author SY
 * @ClassName UserInfo
 * @Description: 用户
 * @Date 2017-04-20 11:40
 */
public class UserInfo implements Serializable {
    private String userId;
    private String userName;
    private String loginName;
    private String loginPassword;
    private String mobilePhone;
    private String emailAdress;
    private String vcCardNo;
    private String cureentStatus;
    private String errorCount;
    private String lockStatus;
    private String currentLoginTime;
    private String curentLoginAdress;
    private String curentLoginIP;

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
}
