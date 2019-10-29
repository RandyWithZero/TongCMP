package com.tongtech.cmp.kube.major;

import com.tongtech.cmp.common.constant.RegexRuleConstant;
import com.tongtech.cmp.kube.exception.KubeResourceNameNotCorrectException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description
 * <p>
 * version 0.1
 * createDate 2019/10/25 16:37
 * updateDate 2019/10/25 16:37
 *
 * @author wangshaoqi
 */
public interface OperationRegion {
    String OP_CREATE = "create";
    String OP_GET = "get";
    String OP_DELETE = "delete";
    String OP_EDIT = "edit";
    String OP_LIST = "list";

    /**
     * 校验kubernetes资源名称 要求名称包含数字、小写字母、- ；且-不能结尾和开头
     *
     * @param name 资源名称
     * @throws KubeResourceNameNotCorrectException 异常
     */
    default void verifyKubeResourceName(String name) throws KubeResourceNameNotCorrectException {
        Pattern compile = Pattern.compile(RegexRuleConstant.KUBE_RESOURCE_NAME_REGEX);
        Matcher matcher = compile.matcher(name == null ? "" : name);
        boolean result = matcher.matches();
        if (!result) {
            throw new KubeResourceNameNotCorrectException();
        }
    }

    /**
     * 调用 增删改查方法
     *
     * @param region 操作域
     * @throws Exception 异常
     */
    default void opRegion(OperationRegion region,String op) throws Exception {
        Class<? extends OperationRegion> aClass = region.getClass();
        switch (op) {
            case OP_CREATE:
                Method create = aClass.getDeclaredMethod(OP_CREATE);
                create.setAccessible(true);
                create.invoke(region);
                break;
            case OP_DELETE:
                Method delete = aClass.getDeclaredMethod(OP_DELETE);
                delete.setAccessible(true);
                delete.invoke(region);
                break;
            case OP_GET:
                Method get = aClass.getDeclaredMethod(OP_GET);
                get.setAccessible(true);
                get.invoke(region);
                break;
            case OP_LIST:
                Method list = aClass.getDeclaredMethod(OP_LIST);
                list.setAccessible(true);
                list.invoke(region);
                break;
            case OP_EDIT:
                Method edit = aClass.getDeclaredMethod(OP_EDIT);
                edit.setAccessible(true);
                edit.invoke(region);
                break;
            default:
        }
    }
}
