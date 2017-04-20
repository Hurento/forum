package com.sy.forum.utils;

import com.sy.forum.core.conts.DateConstants;
import com.sy.forum.exceptions.UnitedException;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author SY
 * @Description: 日期工具类
 * @Date 2017/4/6 10:10
 */
public class DateUtil {

    /**
     * @param date   指定日期
     * @param format 格式化代码
     * @return String    返回类型
     * @Title: parseFormatDate
     * @Description: 日期根据格式转换
     */
    public static String parseFormatDate(Date date, String format) throws UnitedException {
        if (Utils.isEmpty(date)) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * @param format 格式化代码
     * @param number 过去或未来年的年数
     * @return String 返回类型
     * @Title: getYear
     * @Description: 获取过去年或未来年
     */
    public static String getYear(String format, int number) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, number);
        Date y = c.getTime();
        return sdf.format(y);
    }

    /**
     * @param format 格式化代码
     * @return String    返回类型
     * @Title: parseFormatDate
     * @Description: 根据格式化代码格式日期
     */
    public static String parseFormatDate(String format) throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * @param date   字符类型日期
     * @param format 格式化代码
     * @return String    返回类型
     * @Title: parseFormatString
     * @Description: 转日期类型
     */
    public static Date parseFormatString(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(date);
    }

    /**
     * @param date   字符类型日期
     * @param format 格式化代码
     * @return
     * @Title: getLastTime
     * @Description: 格式化当前日期的时分秒为23:59:59
     */
    public static String getLastTime(Date date, String format) throws ParseException {
        if (Utils.isEmpty(date))
            date = new Date();
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * @param date
     * @return
     * @Title: getLastTime
     * @Description: 设置为当天的23点59分59秒
     */
    public static Long getLastTime(Date date) throws ParseException {
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);
        return date.getTime();
    }

    /**
     * @param endDate
     * @param nowDate
     * @return
     * @Title: getDifferenceHour
     * @Description: 返回相差的小时
     */
    public static double getDifferenceHour(Date endDate, Date nowDate) throws ParseException {
        long nh = 1000 * 60 * 60;
        // 获得两个时间的毫秒时间差异
        double diff = nowDate.getTime() - endDate.getTime();
        // 计算差多少天
        // 计算差多少小时
        double hour = diff / nh;
        DecimalFormat df = new DecimalFormat(".00");
        return Double.parseDouble(df.format(hour));

    }

    /**
     * @param endDate
     * @return
     * @Title: getDifferenceHour
     * @Description: 返回指定日期的小时差
     */
    public static double getDifferenceHour(String endDate) throws ParseException {
        return getDifferenceHour(parseFormatString(endDate, DateConstants.DATETIME_Y_M_D), new Date());
    }

    /**
     * @return
     * @Title: getCurrentTimestamp
     * @Description: 获取当前时间戳
     */
    public static Timestamp getCurrentTimestamp() throws ParseException {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * @param datestr
     * @return
     * @Title: getTimestamp
     * @Description: 字符串转时间戳
     */
    public static Timestamp getTimestamp(String datestr) {
        if (datestr == null) {
            return null;
        }
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        try {
            ts = Timestamp.valueOf(datestr);
            return ts;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param date
     * @return
     * @throws Exception
     * @Title: parseDate
     * @Description: 字符串转日期
     */
    public static Date parseDate(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat();
        return sdf.parse(date);
    }

}
