package com.tongtech.cmp.kube.major;

import com.tongtech.cmp.common.constant.RegexRuleConstant;
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
public interface IKubernetesResourceOperate<T> {
    T create() throws ApiException;

    T delete() throws ApiException;

    T get() throws ApiException;

    T list() throws ApiException;

    T edit() throws ApiException;
}
