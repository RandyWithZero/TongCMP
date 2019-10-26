package com.tongtech.cmp.common.constant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 *description 正则表达式
 *
 *version 0.1
 *createDate 2019/10/23 9:01
 *updateDate 2019/10/23 9:01
 *@author wangshaoqi
 */
public final class RegexRuleConstant {
    private RegexRuleConstant(){}
    public final static String KUBE_RESOURCE_NAME_REGEX="^([a-z0-9]+)|([a-z0-9]+[a-z\\-0-9]*[a-z0-9]+)$";

    public static void main(String[] args) {
        Pattern compile = Pattern.compile(KUBE_RESOURCE_NAME_REGEX);
        Matcher abc = compile.matcher("-0ababada-a");
        System.out.println(abc.matches());
    }
}
