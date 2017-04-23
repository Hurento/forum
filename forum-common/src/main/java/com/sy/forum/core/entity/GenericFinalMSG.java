package com.sy.forum.core.entity;

/**
 * @Author SY
 * @ClassName GenericFinalMSG
 * @Description: 消息实体
 * @Date 2017-04-22 23:25
 */
public class GenericFinalMSG {

    public final static String SUCCESS_CODE = "0";// 成功
    public final static String SUCCESS_SAVE_MSG = "保存成功";
    public final static String SUCCESS_EDIT_MSG = "更新成功";
    public final static String SUCCESS_DEL_MSG = "删除成功";
    public final static String SUCCESS_OPERATION_MSG = "操作成功";
    public final static String SUCCESS_LOGIN_MSG = "登录成功";

    public final static String FAILED_CODE = "1";// 失败
    public final static String FAILED_SAVE_MSG = "保存失败";
    public final static String FAILED_EDIT_MSG = "更新失败";
    public final static String FAILED_DEL_MSG = "删除失败";
    public final static String FAILED_OPERATION_MSG = "操作失败";
    public final static String FAILED_UNKNOW_MSG = "系统错误，请联系管理员";
    public final static String FAILED_LOGIN_MSG = "登录失败，用户名与密码错误";
    public final static String FAILED_LOGIN_USER_MSG = "登录失败，用户名错误";
    public final static String FAILED_LOGIN_PASSWORD_MSG = "登录失败，密码错误";
    public final static String FAILED_LOGIN_LOCK_MSG = "您登录错误次数达到5次，用户已被锁定！请明天在尝试登录！";
}
