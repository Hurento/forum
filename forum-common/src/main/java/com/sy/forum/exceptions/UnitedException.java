package com.sy.forum.exceptions;

import com.sy.forum.core.entity.UnitedLogger;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

;

/**
 * @Author SY
 * @Description: 自定义异常类
 * @Date 2017/3/13 17:28
 */
@SuppressWarnings("serial")
public class UnitedException extends Exception {
    /**
     * 错误代码
     */
    private String errorCcode;

    /**
     * 错误信息
     */
    private String errorMessage;

    public UnitedException(Throwable cause) {
        super(cause);
        UnitedLogger.error(cause);
    }

    public UnitedException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        UnitedLogger.error(cause);
    }

    public UnitedException(String errorCcode) {
        super(errorCcode);
        this.errorCcode = errorCcode;
        UnitedLogger.error(errorCcode);
    }

    public UnitedException(String errorCcode, String errorMessage) {
        super(errorCcode);
        this.errorCcode = errorCcode;
        this.errorMessage = errorMessage;
        UnitedLogger.error("error code: " + errorCcode + ", error message: " + errorMessage);
    }

    public String getErrorCode() {
        return errorCcode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: getErrorMessage
     * @Description: 错误代码对应的信息描述
     */
    public String loadErrorMessageByCode(HttpServletRequest request) {
        String errorMessage = null;
        RequestContext requestContext = new RequestContext(request);// 国际化
        errorMessage = requestContext.getMessage(errorCcode);
        return errorMessage;
    }

}
