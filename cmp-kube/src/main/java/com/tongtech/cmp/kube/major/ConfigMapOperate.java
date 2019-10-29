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
public class ConfigMapOperate implements IKubernetesResourceOperate<ConfigMapOperationRegion> {
    private CoreV1Api coreV1Api;

    ConfigMapOperate(CoreV1Api coreV1Api) {
        this.coreV1Api = coreV1Api;
    }

    @Override
    public ConfigMapOperationRegion create() {
        return new ConfigMapOperationRegion(OperationRegion.OP_CREATE, coreV1Api);

    }

    @Override
    public ConfigMapOperationRegion delete() {
        return new ConfigMapOperationRegion(OperationRegion.OP_DELETE, coreV1Api);

    }

    @Override
    public ConfigMapOperationRegion get() {
        return new ConfigMapOperationRegion(OperationRegion.OP_GET, coreV1Api);
    }

    @Override
    public ConfigMapOperationRegion list() {
        return new ConfigMapOperationRegion(OperationRegion.OP_LIST, coreV1Api);

    }
    @Override
    public ConfigMapOperationRegion edit() {
        return new ConfigMapOperationRegion(OperationRegion.OP_EDIT, coreV1Api);
    }

}
