package com.tongtech.cmp.kube.major;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tongtech.cmp.common.constant.ProgramInformationConstant;
import com.tongtech.cmp.kube.exception.KubeResourceNameRequiredException;
import com.tongtech.cmp.kube.exception.KubeResourceNamespaceRequiredException;
import com.tongtech.cmp.kube.model.ConfigMap;
import com.tongtech.cmp.kube.model.HttpPatch;
import com.tongtech.cmp.kube.util.ObjectTranslateUtil;
import com.tongtech.cmp.kube.util.StringUtil;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1ConfigMap;
import io.kubernetes.client.models.V1ConfigMapList;
import io.kubernetes.client.util.Yaml;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

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
public class ConfigMapOperationRegion implements OperationRegion {
    private ConfigMap configMap;
    private String op;
    private CoreV1Api coreV1Api;
    private String returnValue;
    private List<ConfigMap> configMapList;
    private List<String> removeLabelKeys;
    private List<String> removeAnnotationKeys;
    private List<String> removeDataKeys;

    ConfigMapOperationRegion(String op, CoreV1Api coreV1Api) {
        this.op = op;
        this.coreV1Api = coreV1Api;
        this.configMap = new ConfigMap();
    }

    public ConfigMapOperationRegion withName(String name) {
        verifyKubeResourceName(name);
        this.configMap.setName(name);
        return this;
    }

    public ConfigMapOperationRegion withConfigMap(ConfigMap configMap) {
        verifyKubeResourceName(configMap.getName());
        this.configMap = configMap;
        return this;
    }

    public ConfigMapOperationRegion withLabels(Map<String, String> labels) {
        this.configMap.setLabels(labels);
        return this;
    }

    public ConfigMapOperationRegion withNamespace(String namespace) {
        this.configMap.setNamespace(namespace);
        return this;
    }

    public ConfigMapOperationRegion addData(String dataKey, String dataValue) {
        if (this.configMap.getData() == null) {
            this.configMap.setData(new HashMap<>(4));
        }
        this.configMap.getData().put(dataKey, dataValue);
        return this;
    }

    public ConfigMapOperationRegion withData(Map<String, String> data) {
        this.configMap.setData(data);
        return this;
    }

    public ConfigMapOperationRegion addLabel(String labelKey, String labelValue) {
        if (this.configMap.getLabels() == null) {
            this.configMap.setLabels(new HashMap<>(4));
        }
        this.configMap.getLabels().put(labelKey, labelValue);
        return this;
    }

    public ConfigMapOperationRegion withAnnotations(Map<String, String> annotations) {
        this.configMap.setAnnotations(annotations);
        return this;
    }

    public ConfigMapOperationRegion addAnnotation(String annotationKey, String annotationValue) {
        if (this.configMap.getAnnotations() == null) {
            this.configMap.setAnnotations(new HashMap<>(4));
        }
        this.configMap.getAnnotations().put(annotationKey, annotationValue);
        return this;
    }

    public ConfigMapOperationRegion withLabels(String labels) {
        this.configMap.setLabels(StringUtil.stringToMap(labels));
        return this;
    }

    public ConfigMapOperationRegion withAnnotations(String annotations) {
        this.configMap.setAnnotations(StringUtil.stringToMap(annotations));
        return this;
    }

    public ConfigMapOperationRegion removeAnnotation(String annotationKey) {
        if (this.removeAnnotationKeys == null) {
            this.removeAnnotationKeys = new ArrayList<>(4);
        }
        this.removeAnnotationKeys.add(annotationKey);
        return this;
    }

    public ConfigMapOperationRegion removeLabel(String labelKey) {
        if (this.removeLabelKeys == null) {
            this.removeLabelKeys = new ArrayList<>(4);
        }
        this.removeLabelKeys.add(labelKey);
        return this;
    }

    public ConfigMapOperationRegion removeData(String dataKey) {
        if (this.removeDataKeys == null) {
            this.removeDataKeys = new ArrayList<>(4);
        }
        this.removeDataKeys.add(dataKey);
        return this;
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
        V1ConfigMap v1ConfigMap = ObjectTranslateUtil.platFormToKube(configMap);
        coreV1Api.createNamespacedConfigMap(this.configMap.getNamespace(), v1ConfigMap, null, null, null);
        this.returnValue = Yaml.dump(v1ConfigMap);
    }

    private void edit() throws ApiException {
        get();
        String labelPath = "/metadata/labels";
        String annotationPath = "/metadata/annotations";
        String dataPath = "/data";
        List<HttpPatch> httpPatches = new ArrayList<>();
        ConfigMap configMap = ObjectTranslateUtil.kubeToPlatForm(Yaml.loadAs(this.returnValue, V1ConfigMap.class));
        if (configMap.getLabels() == null && this.configMap.getLabels() != null) {
            configMap.setLabels(this.configMap.getLabels());
            httpPatches.add(HttpPatch.newAddOp(labelPath, new HashMap<>(4)));
        } else if (configMap.getLabels() != null && this.configMap.getLabels() != null) {
            configMap.getLabels().putAll(this.configMap.getLabels());
        }
        if (configMap.getAnnotations() == null && this.configMap.getAnnotations() != null) {
            configMap.setAnnotations(this.configMap.getAnnotations());
            httpPatches.add(HttpPatch.newAddOp(annotationPath, new HashMap<>(4)));
        } else if (configMap.getAnnotations() != null && this.configMap.getAnnotations() != null) {
            configMap.getAnnotations().putAll(this.configMap.getAnnotations());
        }
        if (configMap.getData() == null && this.configMap.getData() != null) {
            configMap.setData(this.configMap.getData());
            httpPatches.add(HttpPatch.newAddOp(dataPath, new HashMap<>(4)));
        } else if (configMap.getData() != null && this.configMap.getData() != null) {
            configMap.getData().putAll(this.configMap.getData());
        }
        for (Map.Entry<String, String> entry : configMap.getLabels().entrySet()) {
            httpPatches.add(HttpPatch.newAddOp(labelPath + "/" + entry.getKey(), entry.getValue()));
        }
        for (Map.Entry<String, String> entry : configMap.getAnnotations().entrySet()) {
            httpPatches.add(HttpPatch.newAddOp(annotationPath + "/" + entry.getKey(), entry.getValue()));
        }
        for (Map.Entry<String, String> entry : configMap.getData().entrySet()) {
            httpPatches.add(HttpPatch.newAddOp(dataPath + "/" + entry.getKey(), entry.getValue()));
        }
        if (this.removeLabelKeys != null) {
            for (String key : this.removeLabelKeys) {
                if (configMap.getLabels().containsKey(key)) {
                    httpPatches.add(HttpPatch.newRemoveOp(labelPath + "/" + key));
                }
            }
        }
        if (this.removeAnnotationKeys != null) {
            for (String key : this.removeAnnotationKeys) {
                if (configMap.getAnnotations().containsKey(key)) {
                    httpPatches.add(HttpPatch.newRemoveOp(annotationPath + "/" + key));
                }
            }
        }
        if (this.removeDataKeys != null) {
            for (String key : this.removeDataKeys) {
                if (configMap.getData().containsKey(key)) {
                    httpPatches.add(HttpPatch.newRemoveOp(dataPath + "/" + key));
                }
            }
        }
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String httpPatchesStr = gson.toJson(httpPatches);
        io.kubernetes.client.custom.V1Patch v1Patch = new io.kubernetes.client.custom.V1Patch(httpPatchesStr);
        coreV1Api.patchNamespacedConfigMap(configMap.getName(), configMap.getNamespace(), v1Patch, null, null, null, null);
        this.returnValue = Yaml.dump(configMap);
    }

    private void get() throws ApiException {
        if (this.configMap.getName() == null || this.configMap.getName().isEmpty()) {
            throw new KubeResourceNameRequiredException();
        }
        if (this.configMap.getNamespace() == null || this.configMap.getNamespace().isEmpty()) {
            throw new KubeResourceNamespaceRequiredException();
        }
        V1ConfigMap v1ConfigMap = coreV1Api.readNamespacedConfigMap(this.configMap.getName(), this.configMap.getNamespace(), null, null, null);
        this.returnValue = Yaml.dump(v1ConfigMap);
    }

    private void list() throws ApiException {
        if (this.configMap.getNamespace() == null || this.configMap.getNamespace().isEmpty()) {
            throw new KubeResourceNamespaceRequiredException();
        }
        List<ConfigMap> configMaps = new ArrayList<>();
        String labelStr = StringUtil.mapToString(this.configMap.getLabels());
        V1ConfigMapList v1ConfigMapList = coreV1Api.listNamespacedConfigMap(this.configMap.getNamespace(), null, null, null, labelStr, null, null, null, false);
        for (V1ConfigMap v1ConfigMap : v1ConfigMapList.getItems()) {
            ConfigMap configMap = ObjectTranslateUtil.kubeToPlatForm(v1ConfigMap);
            configMaps.add(configMap);
        }
        this.configMapList = configMaps;
        this.returnValue = Yaml.dump(configMaps);
    }

    private void delete() throws ApiException {
        if (this.configMap.getName() != null) {
            coreV1Api.deleteNamespacedConfigMap(this.configMap.getName(), this.configMap.getNamespace(), null, null, null, null, false, null);
        }
        //若资源标签为默认标签，则不需操作
        if (StringUtil.compareMap(this.configMap.getLabels(),StringUtil.stringToMap(ProgramInformationConstant.PROGRAM_DEFAULT_LABELS))) {
            return;
        }
        list();
        for (ConfigMap configMapItem : this.configMapList) {
            if (configMapItem.getName().equals(this.configMap.getName())) {
                continue;
            }
            coreV1Api.deleteNamespacedConfigMap(configMapItem.getName(), configMapItem.getNamespace(), null, null, null, null, false, null);

        }

    }
}
