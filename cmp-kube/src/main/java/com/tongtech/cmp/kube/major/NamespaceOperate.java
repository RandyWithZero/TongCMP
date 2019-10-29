package com.tongtech.cmp.kube.major;

import io.kubernetes.client.apis.CoreV1Api;

/**
 * description
 * <p>
 * version 0.1
 * createDate 2019/10/25 15:49
 * updateDate 2019/10/25 15:49
 *
 * @author wangshaoqi
 */
public class NamespaceOperate implements IKubernetesResourceOperate<NamespaceOperationRegion> {
    private CoreV1Api coreV1Api;

    NamespaceOperate(CoreV1Api coreV1Api) {
        this.coreV1Api = coreV1Api;
    }

    @Override
    public NamespaceOperationRegion create() {
        return new NamespaceOperationRegion(OperationRegion.OP_CREATE, coreV1Api);

    }

    @Override
    public NamespaceOperationRegion delete() {
        return new NamespaceOperationRegion(OperationRegion.OP_DELETE, coreV1Api);

    }

    @Override
    public NamespaceOperationRegion get() {
        return new NamespaceOperationRegion(OperationRegion.OP_GET, coreV1Api);
    }

    @Override
    public NamespaceOperationRegion list() {
        return new NamespaceOperationRegion(OperationRegion.OP_LIST, coreV1Api);


    }

    @Override
    public NamespaceOperationRegion edit() {

        return new NamespaceOperationRegion(OperationRegion.OP_EDIT, coreV1Api);
    }

}
