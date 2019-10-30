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
public class StatefulSetOperate implements IKubernetesResourceOperate<StatefulSetOperationRegion> {
    private AppsV1Api appsV1Api;

    StatefulSetOperate(AppsV1Api appsV1Api) {
        this.appsV1Api = appsV1Api;
    }

    @Override
    public StatefulSetOperationRegion create() {
        return new StatefulSetOperationRegion(OperationRegion.OP_CREATE, appsV1Api);
    }

    @Override
    public StatefulSetOperationRegion delete() {
        return new StatefulSetOperationRegion(OperationRegion.OP_DELETE, appsV1Api);
    }

    @Override
    public StatefulSetOperationRegion get() {
        return new StatefulSetOperationRegion(OperationRegion.OP_GET, appsV1Api);
    }

    @Override
    public StatefulSetOperationRegion list() {
        return new StatefulSetOperationRegion(OperationRegion.OP_LIST, appsV1Api);

    }

    @Override
    public StatefulSetOperationRegion edit() {
        return new StatefulSetOperationRegion(OperationRegion.OP_EDIT, appsV1Api);
    }

}
