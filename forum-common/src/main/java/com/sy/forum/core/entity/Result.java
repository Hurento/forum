package com.sy.forum.core.entity;

import java.io.Serializable;

/**
 * @Author SY
 * @Description: 响应的结果对象
 * @Date 2017/3/13 17:32
 */
public class Result implements Serializable {
    private static final long serialVersionUID = 6288374846131788743L;

    /**
     * 信息
     */
    private String message;

    /**
     * 信息状态码
     */
    private String messageCode;

    /**
     * 结果状态
     */
    private String resultCode;

    /**
     * 响应的参数
     */
    private String responseParam;

    private Object data;//

    public Result() {
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResponseParam() {
        return responseParam;
    }

    public void setResponseParam(String responseParam) {
        this.responseParam = responseParam;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
