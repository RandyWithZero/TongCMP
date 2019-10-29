package com.tongtech.cmp.kube.major;

import com.tongtech.cmp.kube.model.ConfigMap;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.apis.AppsV1Api;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * description
 * <p>
 * version 0.1
 * createDate 2019/10/25 16:39
 * updateDate 2019/10/25 16:39
 *
 * @author wangshaoqi
 */
@Slf4j
public class DeploymentOperationRegion implements OperationRegion {
    private String op;
    private AppsV1Api appsV1Api;
    private String returnValue;
    private List<ConfigMap> configMapList;
    private List<String> removeLabelKeys;
    private List<String> removeAnnotationKeys;
    private List<String> removeDataKeys;

    DeploymentOperationRegion(String op, AppsV1Api appsV1Api) {
        this.appsV1Api = appsV1Api;
    }


    public String doneReturnYamlStr() throws Exception {
        doneVoid();
        return this.returnValue;
    }

    public List<ConfigMap> doneReturnList() throws Exception {
        doneVoid();
        return this.configMapList;
    }

    public void doneVoid() throws Exception {
        opRegion(this, op);
    }

    private void create() throws ApiException {

    }

    private void edit() throws ApiException {

    }

    private void get() throws ApiException {

    }

    private void list() throws ApiException {

    }

    private void delete() throws ApiException {

    }
}
