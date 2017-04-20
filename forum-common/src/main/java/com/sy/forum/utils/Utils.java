package com.sy.forum.utils;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @ClassName: WebServiceUtils
 * @Description: WebService帮助类
 * @date 2016-4-5 下午1:27:14
 */
public class Utils {

    /**
     * 是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null || "".equals(obj)) {
            return true;
        }
        if (obj instanceof Collection) {
            @SuppressWarnings("rawtypes")
            Collection coll = (Collection) obj;
            if (coll.size() <= 0) {
                return true;
            }
        }
        if (obj instanceof Map) {
            @SuppressWarnings("rawtypes")
            Map map = (Map) obj;
            return map.isEmpty();
        }
        if (obj instanceof Object[]) {
            Object[] objs = (Object[]) obj;
            boolean flag = false;
            if (objs.length <= 0)
                flag = true;
            for (Object objl : objs) {
                if (objl instanceof String) {
                    String str = (String) objl;
                    if (str == null || str == "null" || "".equals(str) || str.length() <= 0) {
                        flag = true;
                        break;
                    }
                }
            }
            return flag;
        }
        return false;
    }

    /**
     * 是否是整数
     *
     * @param strs
     * @return
     */
    public static boolean isNumber(String... strs) {
        if (isEmpty(strs))
            return false;
        Pattern pattern = Pattern.compile("\\d*");
        boolean isNumber = true;
        for (String str : strs) {
            isNumber = pattern.matcher(str).matches();
        }
        return isNumber;
    }

    /**
     * @param str
     * @param oldSymbol
     * @param newSymbol
     * @return String 返回类型
     * @Title: replaceSymbol
     * @Description: 替换符号
     */
    public static String replaceSymbol(String str, String oldSymbol, String newSymbol) {
        if (!Utils.isEmpty(str)) {
            str = str.replace(oldSymbol, newSymbol);
        }
        return str;
    }

    /**
     * @param str      字符串
     * @param splitStr 分割符号
     * @return String[] 返回类型
     * @Title: splitString
     * @Description: 分割字符串
     */
    public static String[] splitString(String str, String splitStr) {
        String[] strs = null;
        if (!Utils.isEmpty(str) && !Utils.isEmpty(splitStr)) {
            strs = str.split(splitStr);
        }
        return strs;
    }

    /**
     * @param fileName 文件的名称
     * @return boolean 返回类型
     * @Title: isExcel2003
     * @Description: 是否是2003的excel，返回true是2003
     */
    public static boolean isExcel2003(String fileName) {
        return fileName.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * @param fileName 文件的名称
     * @return boolean 返回类型
     * @Title: isExcel2007
     * @Description: 是否是2007的excel，返回true是2007
     */
    public static boolean isExcel2007(String fileName) {
        return fileName.matches("^.+\\.(?i)(xlsx)$");
    }
}
