package com.tongtech.cmp.kube.major;

import io.kubernetes.client.apis.AppsV1Api;
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
public class ServiceOperate implements IKubernetesResourceOperate<ServiceOperationRegion> {
    private CoreV1Api coreV1Api;

    ServiceOperate(CoreV1Api coreV1Api) {
        this.coreV1Api = coreV1Api;
    }

    @Override
    public ServiceOperationRegion create() {
        return new ServiceOperationRegion(OperationRegion.OP_CREATE, coreV1Api);
    }

    @Override
    public ServiceOperationRegion delete() {
        return new ServiceOperationRegion(OperationRegion.OP_DELETE, coreV1Api);
    }

    @Override
    public ServiceOperationRegion get() {
        return new ServiceOperationRegion(OperationRegion.OP_GET, coreV1Api);
    }

    @Override
    public ServiceOperationRegion list() {
        return new ServiceOperationRegion(OperationRegion.OP_LIST, coreV1Api);

    }

    @Override
    public ServiceOperationRegion edit() {
        return new ServiceOperationRegion(OperationRegion.OP_EDIT, coreV1Api);
    }

}
