package com.tongtech.cmp.kube.major;

import io.kubernetes.client.apis.AppsV1Api;

/**
 * description
 * <p>
 * version 0.1
 * createDate 2019/10/25 15:49
 * updateDate 2019/10/25 15:49
 *
 * @author wangshaoqi
 */
public class DaemonSetOperate implements IKubernetesResourceOperate<DaemonSetOperationRegion> {
    private AppsV1Api appsV1Api;

    DaemonSetOperate(AppsV1Api appsV1Api) {
        this.appsV1Api = appsV1Api;
    }

    @Override
    public DaemonSetOperationRegion create() {
        return new DaemonSetOperationRegion(OperationRegion.OP_CREATE, appsV1Api);

    }

    @Override
    public DaemonSetOperationRegion delete() {
        return new DaemonSetOperationRegion(OperationRegion.OP_DELETE, appsV1Api);

    }

    @Override
    public DaemonSetOperationRegion get() {
        return new DaemonSetOperationRegion(OperationRegion.OP_GET, appsV1Api);
    }

    @Override
    public DaemonSetOperationRegion list() {
        return new DaemonSetOperationRegion(OperationRegion.OP_LIST, appsV1Api);


    }

    @Override
    public DaemonSetOperationRegion edit() {
        return new DaemonSetOperationRegion(OperationRegion.OP_EDIT, appsV1Api);
    }

}
