package com.chris.framework.builder.utils;

/**
 * ChrisFrameworkObjectBuilder
 * com.chris.framework.builder.utils
 * Created by Chris Chen
 * 2018/1/16
 * Explain:处理字符串的工具类
 */
public class StringUtils {
    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }
    public static String getLowerCamel(String str) {
        int i = str.indexOf("_");
        char c = str.charAt(i + 1);
        str = str.replace("_" + c, String.valueOf(c).toUpperCase());
        if (i < 0) {
            return lowerFirstLetter(str);
        }
        return getLowerCamel(str);
    }

    public static String lowerFirstLetter(String str) {
        String firstLetter = String.valueOf(str.charAt(0));
        return firstLetter.toLowerCase() + str.substring(1);
    }
}
