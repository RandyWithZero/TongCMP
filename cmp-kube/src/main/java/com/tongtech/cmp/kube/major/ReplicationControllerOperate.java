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
public class ReplicationControllerOperate implements IKubernetesResourceOperate<ReplicationControllerOperateRegion> {
    private AppsV1Api appsV1Api;

    ReplicationControllerOperate(AppsV1Api appsV1Api) {
        this.appsV1Api = appsV1Api;
    }

    @Override
    public ReplicationControllerOperateRegion create() {
        return new ReplicationControllerOperateRegion(OperationRegion.OP_CREATE, appsV1Api);
    }

    @Override
    public ReplicationControllerOperateRegion delete() {
        return new ReplicationControllerOperateRegion(OperationRegion.OP_DELETE, appsV1Api);
    }

    @Override
    public ReplicationControllerOperateRegion get() {
        return new ReplicationControllerOperateRegion(OperationRegion.OP_GET, appsV1Api);
    }

    @Override
    public ReplicationControllerOperateRegion list() {
        return new ReplicationControllerOperateRegion(OperationRegion.OP_LIST, appsV1Api);

    }

    @Override
    public ReplicationControllerOperateRegion edit() {
        return new ReplicationControllerOperateRegion(OperationRegion.OP_EDIT, appsV1Api);
    }

}
