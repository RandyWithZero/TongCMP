package com.tongtech.cmp.kube.apis;

import com.tongtech.cmp.kube.NamespaceOperationRegion;
import com.tongtech.cmp.kube.OperationRegion;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Namespace;

/**
 * description
 * <p>
 * version 0.1
 * createDate 2019/10/25 15:49
 * updateDate 2019/10/25 15:49
 *
 * @author wangshaoqi
 */
public class NamespaceOperateImpl {
    private CoreV1Api coreV1Api;

    NamespaceOperateImpl(CoreV1Api coreV1Api) {
        this.coreV1Api = coreV1Api;
    }

    public NamespaceOperationRegion create() {
        return help(OperationRegion.OP_CREATE);

    }

    public NamespaceOperationRegion delete() {
        return help(OperationRegion.OP_DELETE);

    }

    public NamespaceOperationRegion get() {
        return help(OperationRegion.OP_GET);

    }

    public NamespaceOperationRegion list() {
        return help(OperationRegion.OP_LIST);

    }

    public NamespaceOperationRegion edit() {
        return help(OperationRegion.OP_EDIT);
    }

    private NamespaceOperationRegion help(String op) {
        NamespaceOperationRegion namespaceCreateRegion = new NamespaceOperationRegion();
        namespaceCreateRegion.setOp(op);
        namespaceCreateRegion.setCoreV1Api(coreV1Api);
        return namespaceCreateRegion;
    }
}
