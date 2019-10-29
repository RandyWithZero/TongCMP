package com.tongtech.cmp.kube.exception;

import com.tongtech.cmp.common.exception.TongCmpException;

/**
 * description kubernetes资源 名称包含数字、小写字母、- 且-不能结尾和开头
 * <p>
 * version 0.1
 * createDate 2019/10/22 18:28
 * updateDate 2019/10/22 18:28
 *
 * @author wangshaoqi
 */
public class KubeResourceNamespaceRequiredException extends TongCmpException {
    private final static String MESSAGE = "资源命名空间 'namespace' (必须)";

    public KubeResourceNamespaceRequiredException() {
        super(MESSAGE);
    }
}
