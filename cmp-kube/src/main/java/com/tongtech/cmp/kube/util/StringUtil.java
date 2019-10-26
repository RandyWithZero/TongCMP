package com.tongtech.cmp.kube.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * description  字符串工具类
 * <p>
 * version 0.1
 * createDate 2019/10/21 13:43
 * updateDate 2019/10/21 13:43
 *
 * @author wangshaoqi
 */
public final class StringUtil {
    private StringUtil() {
    }

    /**
     * 将map中的项 变成字符串 格式如：a=b，c=d
     *
     * @param map 目标MAP
     */
    public static String mapToString(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String, String>> entries = map.entrySet();
        String split = ",";
        for (Map.Entry<String, String> entry : entries) {
            String k = entry.getKey();
            String v = entry.getValue();
            if (k == null) {
                continue;
            }
            if (v == null) {
                v = "";
            }
            sb.append(k).append("=").append(v).append(split);
        }
        String sbStr = sb.toString();
        return sbStr.substring(0, sbStr.lastIndexOf(split));
    }

    /**
     * 将一定规格的字符串分割放入map中
     *
     * @param str 目标Str
     */
    public static Map<String, String> stringToMap(String str) {
        HashMap<String, String> map = new HashMap<>(4);
        String[] labelArray = str.split(",");
        for (String labelItem : labelArray) {
            String labelKey = labelItem.substring(0, labelItem.indexOf("="));
            String labelValue = labelItem.substring(labelItem.indexOf("=") + 1);
            map.put(labelKey, labelValue);
        }
        return map;
    }
}
