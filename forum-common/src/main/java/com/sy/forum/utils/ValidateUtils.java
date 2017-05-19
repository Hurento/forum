package com.sy.forum.utils;

import java.util.regex.Pattern;

/**
 * @Author SY
 * @Description: 正则表达式验证
 * @Date 2017/5/18 16:21
 */
public class ValidateUtils {
    // Regular expression：
    // Verify phone number format
    public final static String REGEX_PHONE = "0[0-9]{2,3}-[1-9][0-9]{6,7}";
    // Verify mobile number format
    public final static String REGEX_mobile = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    // Verify mailbox format
    public final static String REGEX_EMAIL = "^([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
    // Validation number
    public final static String REGEX_NUMBER_D = "\\d*";
    // Validate digital format
    public final static String REGEX_NUMBER = "^-?[1-9]+(\\.\\d+)?$|^-?0(\\.\\d+)?$|^-?[1-9]+[0-9]*(\\.\\d+)?$";
    // Verify excel type
    public final static String REGEX_XLS = "^.+\\.(?i)(xls)$";
    public final static String REGEX_XLSX = "^.+\\.(?i)(xlsx)$";
    // Verify user length
    public final static String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";
    // Verify password length
    public final static String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";
    // Verify Chinese
    public final static String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";
    // Verify ID card
    public final static String REGEX_ID_CARD = "(^\\d{17}[0-9|x|X]$)|(^\\d{15}$)";
    // Verify IP address
    public static final String REGEX_IP_ADDR = "(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";
    // Verify url ([]{3})\\.([0-255]{3})\\.([0-255]{3})\\.([0-255]{3})
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%";

    /**
     * Replacement string
     * @Acthor：Carl
     * @Param param：String
     * @Param oldSymbol：start char
     * @Param newSymbol：end char
     * @Date 2016-09-29 16:02
     * @return String
     */
    public static String replaceSymbol(String param,String oldSymbol,String newSymbol) {
        if(!Utils.isEmpty(param)) {
            param = param.replace(oldSymbol, newSymbol);
        }
        return param;
    }

    /**
     *  Split string
     * @Acthor：Carl
     * @Param param：String
     * @Param splitStr：Split char
     * @Date 2016-09-29 16:04
     * @return
     */
    public static String[] splitString(String param, String splitStr) {
        String[] strs = null;
        if(!Utils.isEmpty(param) && !Utils.isEmpty(splitStr)) {
            strs = param.split(splitStr);
        }
        return strs;
    }

    /**
     * Is it has trim
     * @Acthor：Carl
     * @Param
     * @Date 2016-09-29 16:08
     * @return
     */
    public static boolean isSpace(String param) {
        if (param.trim().length() >= 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Universal verification regular expression
     * @Acthor：Carl
     * @Param regex：regular expression
     * @Param param
     * @Date 2016-09-29 16:26
     * @return
     */
    public static boolean isGeneral(String regex, String param) {
        boolean flag = false;
        if (!Utils.isEmpty(param)) {
            Pattern p = Pattern.compile(regex);
            flag = p.matcher(param).matches();
        }
        return flag;
    }

    /**
     * Validation number
     * @Acthor：Carl
     * @Param param
     * @Date 2016-09-29 16:01
     * @return boolean
     */
    public static boolean isNumbers(String param) {
        return isGeneral(REGEX_NUMBER_D, param);
    }

    /**
     * Validate digital format
     * @Acthor：Carl
     * @Param param
     * @Date 2016-09-29 16:16
     * @return
     */
    public static boolean isNumber(String param) {
        return isGeneral(REGEX_NUMBER, param);
    }

    /**
     * Verify mailbox format
     * @Acthor：Carl
     * @Param param
     * @Date 2016-09-29 16:17
     * @return
     */
    public static boolean isEmail(String param) {
        return isGeneral(REGEX_EMAIL, param);

    }

    /**
     * Verify phone number format
     * @Acthor：Carl
     * @Param param
     * @Date 2016-09-29 16:24
     * @return
     */
    public static boolean isPhone(String param) {
        return isGeneral(REGEX_PHONE, param);
    }

    /**
     * Verify mobile number format
     * @Acthor：Carl
     * @Param param
     * @Date 2016-09-29 16:20
     * @return 
     */
    public static boolean isMobile(String param) {
        return isGeneral(REGEX_mobile, param);
    }

    public static void main(String[] as) {
        System.err.println(ValidateUtils.isGeneral(REGEX_IP_ADDR, "223.255.12.12"));
    }
}
