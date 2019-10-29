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
public class DeploymentOperate implements IKubernetesResourceOperate<DeploymentOperationRegion> {
    private AppsV1Api appsV1Api;

    DeploymentOperate(AppsV1Api appsV1Api) {
        this.appsV1Api = appsV1Api;
    }

    @Override
    public DeploymentOperationRegion create() {
        return new DeploymentOperationRegion(OperationRegion.OP_CREATE, appsV1Api);

    }

    @Override
    public DeploymentOperationRegion delete() {
        return new DeploymentOperationRegion(OperationRegion.OP_DELETE, appsV1Api);

    }

    @Override
    public DeploymentOperationRegion get() {
        return new DeploymentOperationRegion(OperationRegion.OP_GET, appsV1Api);
    }

    @Override
    public DeploymentOperationRegion list() {
        return new DeploymentOperationRegion(OperationRegion.OP_LIST, appsV1Api);


    }

    @Override
    public DeploymentOperationRegion edit() {

        return new DeploymentOperationRegion(OperationRegion.OP_EDIT, appsV1Api);
    }

}
