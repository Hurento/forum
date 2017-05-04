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
    public final static String SUCCESS_LOGOUT_MSG = "退出登录成功";

    public final static String FAILED_CODE = "1";// 失败
    public final static String FAILED_SAVE_MSG = "保存失败";
    public final static String FAILED_EDIT_MSG = "更新失败";
    public final static String FAILED_DEL_MSG = "删除失败";
    public final static String FAILED_OPERATION_MSG = "操作失败";
    public final static String FAILED_UNKNOW_MSG = "系统内部错误，请联系管理员";
    public final static String FAILED_LOGIN_UNKNOW_MSG = "登录失败，未知的用户";
    public final static String FAILED_LOGIN_MSG = "登录失败，用户名与密码错误";
    public final static String FAILED_LOGIN_USER_MSG = "登录失败，用户名错误";
    public final static String FAILED_LOGIN_PASSWORD_MSG = "登录失败，密码错误";
    public final static String FAILED_LOGIN_DISABLE_MSG = "用户已被禁用！";
    public final static String FAILED_LOGIN_LOCK_MSG = "您输入错误密码次数太多，用户已被锁定！请明天在尝试登录！";
    public final static String FAILED_LOGIN_ENTERERROR_MSG = "您输入错误密码次数太多！COUNT次后用户将被锁定！";
}
