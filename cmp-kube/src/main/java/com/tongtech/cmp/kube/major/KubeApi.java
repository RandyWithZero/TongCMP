package com.tongtech.cmp.kube.major;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.AppsV1Api;
import io.kubernetes.client.apis.BatchV1Api;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.apis.RbacAuthorizationApi;
import io.kubernetes.client.util.ClientBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * description kubernetes api 封装类
 * <p>
 * version 0.1
 * createDate 2019/10/18 15:35
 * updateDate 2019/10/18 15:35
 *
 * @author wangshaoqi
 */
@Slf4j
public class KubeApi {
    private ApiClient apiClient;
    private AppsV1Api appsV1Api;
    private CoreV1Api coreV1Api;
    private RbacAuthorizationApi rbacAuthorizationApi;
    private BatchV1Api batchV1Api;

    KubeApi(ApiClient apiClient) {
        this.apiClient = apiClient;
        Configuration.setDefaultApiClient(this.apiClient);
    }


    public NamespaceOperate namespaceOperate() {
        coreV1Api();
        return new NamespaceOperate(this.coreV1Api);
    }

    public ConfigMapOperate configMapOperate() {
        coreV1Api();
        return new ConfigMapOperate(this.coreV1Api);
    }

    public DeploymentOperate deploymentOperate() {
        appsV1Api();
        return new DeploymentOperate(this.appsV1Api);
    }

    public DaemonSetOperate daemonSetOperate() {
        appsV1Api();
        return new DaemonSetOperate(this.appsV1Api);
    }

    public ReplicaSetOperate replicaSetOperate() {
        appsV1Api();
        return new ReplicaSetOperate(this.appsV1Api);
    }

    public ReplicationControllerOperate replicationControllerOperate() {
        appsV1Api();
        return new ReplicationControllerOperate(this.appsV1Api);
    }

    public StatefulSetOperate statefulSetOperate() {
        appsV1Api();
        return new StatefulSetOperate(this.appsV1Api);
    }
    public ServiceOperate serviceOperate() {
        coreV1Api();
        return new ServiceOperate(this.coreV1Api);
    }


    private synchronized void appsV1Api() {
        if (this.appsV1Api == null) {
            this.appsV1Api = new AppsV1Api(this.apiClient);
        }
    }

    private synchronized void batchV1Api() {
        if (this.batchV1Api == null) {
            this.batchV1Api = new BatchV1Api(this.apiClient);
        }
    }

    private synchronized void coreV1Api() {
        if (this.coreV1Api == null) {
            this.coreV1Api = new CoreV1Api(this.apiClient);
        }
    }

    private synchronized void rbacAuthorizationApi() {
        if (this.rbacAuthorizationApi == null) {
            this.rbacAuthorizationApi = new RbacAuthorizationApi(this.apiClient);
        }
    }
}
