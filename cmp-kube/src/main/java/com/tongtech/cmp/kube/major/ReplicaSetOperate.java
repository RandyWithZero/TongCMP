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
public class ReplicaSetOperate implements IKubernetesResourceOperate<ReplicaSetOperationRegion> {
    private AppsV1Api appsV1Api;

    ReplicaSetOperate(AppsV1Api appsV1Api) {
        this.appsV1Api = appsV1Api;
    }

    @Override
    public ReplicaSetOperationRegion create() {
        return new ReplicaSetOperationRegion(OperationRegion.OP_CREATE, appsV1Api);
    }

    @Override
    public ReplicaSetOperationRegion delete() {
        return new ReplicaSetOperationRegion(OperationRegion.OP_DELETE, appsV1Api);
    }

    @Override
    public ReplicaSetOperationRegion get() {
        return new ReplicaSetOperationRegion(OperationRegion.OP_GET, appsV1Api);
    }

    @Override
    public ReplicaSetOperationRegion list() {
        return new ReplicaSetOperationRegion(OperationRegion.OP_LIST, appsV1Api);

    }

    @Override
    public ReplicaSetOperationRegion edit() {
        return new ReplicaSetOperationRegion(OperationRegion.OP_EDIT, appsV1Api);
    }

}
