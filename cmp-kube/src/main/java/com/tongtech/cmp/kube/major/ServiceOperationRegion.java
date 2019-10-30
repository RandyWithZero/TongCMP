package com.tongtech.cmp.kube.major;

import com.tongtech.cmp.kube.model.ConfigMap;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.apis.AppsV1Api;
import io.kubernetes.client.apis.CoreV1Api;
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
public class ServiceOperationRegion implements OperationRegion {
    private String op;
    private CoreV1Api coreV1Api;
    private String returnValue;
    private List<ConfigMap> configMapList;
    private List<String> removeLabelKeys;
    private List<String> removeAnnotationKeys;
    private List<String> removeDataKeys;

    ServiceOperationRegion(String op, CoreV1Api coreV1Api) {
        this.op=op;
        this.coreV1Api = coreV1Api;
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
        //todo

    }

    private void edit() throws ApiException {
        //todo

    }

    private void get() throws ApiException {
        //todo

    }

    private void list() throws ApiException {
        //todo

    }

    private void delete() throws ApiException {
        //todo

    }
}
