package com.sy.forum.utils;

import com.sy.forum.core.entity.UnitedLogger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @Author SY
 * @Description: 解析配置并读取配置项工具类
 * @Date 2017/3/13 18:23
 */
public class PropertiesUtil {
    private static InputStream inputStream;
    private static InputStreamReader read;

    /**
     * @param @param  fileName
     * @param @param  key
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: loadValue
     * @Description: 获取键值
     */
    public static String loadValue(String fileName, String key) {
        String result = null;
        // 获取配置文件
        Properties properties = loadProperties(fileName);
        if (properties != null) {
            // 获取键值
            result = properties.get(key).toString();
        }
        // 读取完成后关闭流的操作
        closeRead();
        return result;
    }

    /**
     * @param fileName 设定文件
     * @return void 返回类型
     * @throws
     * @Title: loadProperties
     * @Description: 获取配置文件
     */
    public static Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
        try {
            read = new InputStreamReader(inputStream, "UTF8");
            //properties.load(read);
        } catch (Exception e) {
            properties = null;
            read = null;
            inputStream = null;
            UnitedLogger.debug(e);
        }
        return properties;
    }

    /**
     * @return void 返回类型
     * @throws
     * @Title: closeRead
     * @Description: 关闭流
     */
    private static void closeRead() {
        if (read != null && inputStream != null) {
            try {
                read.close();
                inputStream.close();
            } catch (Exception e) {
                UnitedLogger.debug(e);
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    UnitedLogger.debug(e);
                }
            }
        }
    }
}
