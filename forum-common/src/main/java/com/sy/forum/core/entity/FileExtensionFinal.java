package com.sy.forum.core.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author SY
 * @ClassName FileExtensionFinal
 * @Description: 资源格式
 * @Date 2017-04-25 21:23
 */
public class FileExtensionFinal {

    public final static String FILE_EXTENSION_JSP = ".jpg";
    public final static String FILE_EXTENSION_PNG = ".png";
    public final static String FILE_EXTENSION_JPG = ".jpg";
    public final static String FILE_EXTENSION_GIF = ".gif";
    public final static String FILE_EXTENSION_BMP = ".bmp";
    public final static String FILE_EXTENSION_JS = ".js";
    public final static String FILE_EXTENSION_CSS = ".css";
    public final static String FILE_EXTENSION_EOT = ".eot";
    public final static String FILE_EXTENSION_SVG = ".svg";
    public final static String FILE_EXTENSION_TTF = ".ttf";
    public final static String FILE_EXTENSION_WOFF = ".woff";
    public final static String FILE_EXTENSION_WOFF2 = ".woff2";


    /**
     * 组装所有的资源文件格式
     * @return
     */
    public static Set<String> getIgnoreExt() {
        Set<String> ignoreExt = new HashSet<>();
        ignoreExt.add(FILE_EXTENSION_JSP);
        ignoreExt.add(FILE_EXTENSION_PNG);
        ignoreExt.add(FILE_EXTENSION_JPG);
        ignoreExt.add(FILE_EXTENSION_GIF);
        ignoreExt.add(FILE_EXTENSION_BMP);
        ignoreExt.add(FILE_EXTENSION_JS);
        ignoreExt.add(FILE_EXTENSION_CSS);
        ignoreExt.add(FILE_EXTENSION_EOT);
        ignoreExt.add(FILE_EXTENSION_SVG);
        ignoreExt.add(FILE_EXTENSION_TTF);
        ignoreExt.add(FILE_EXTENSION_WOFF);
        ignoreExt.add(FILE_EXTENSION_WOFF2);
        return ignoreExt;
    }
}
