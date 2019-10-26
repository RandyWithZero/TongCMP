package com.tongtech.cmp.kube.apis;

import com.tongtech.cmp.common.constant.RegexRuleConstant;
import com.tongtech.cmp.kube.OperationRegion;
import com.tongtech.cmp.kube.exception.KubeResourceNameNotCorrectException;
import io.kubernetes.client.ApiException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description
 * <p>
 * version 0.1
 * createDate 2019/10/25 15:47
 * updateDate 2019/10/25 15:47
 *
 * @author wangshaoqi
 */
public interface IKubernetesResourceOperate {
    OperationRegion create() throws ApiException;

    OperationRegion delete() throws ApiException;

    OperationRegion get() throws ApiException;

    OperationRegion list() throws ApiException;

    OperationRegion edit() throws ApiException;


    /**
     * 校验kubernetes资源名称 要求名称包含数字、小写字母、- ；且-不能结尾和开头
     *
     * @param name 资源名称
     * @throws KubeResourceNameNotCorrectException 异常
     */
    default void verifyKubeResourceName(String name) throws KubeResourceNameNotCorrectException {
        Pattern compile = Pattern.compile(RegexRuleConstant.KUBE_RESOURCE_NAME_REGEX);
        Matcher matcher = compile.matcher(name);
        boolean result = matcher.matches();
        if (!result) {
            throw new KubeResourceNameNotCorrectException();
        }
    }

}
