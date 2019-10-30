package com.tongtech.cmp.kube.major;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.tongtech.cmp.common.constant.ProgramInformationConstant;
import com.tongtech.cmp.kube.exception.KubeResourceNameRequiredException;
import com.tongtech.cmp.kube.model.HttpPatch;
import com.tongtech.cmp.kube.model.Namespace;
import com.tongtech.cmp.kube.util.ObjectTranslateUtil;
import com.tongtech.cmp.kube.util.StringUtil;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Namespace;
import io.kubernetes.client.models.V1NamespaceList;
import io.kubernetes.client.util.Yaml;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class NamespaceOperationRegion implements OperationRegion {
    private String op;
    private CoreV1Api coreV1Api;
    private Namespace namespace;
    private String returnValue;
    private List<Namespace> namespaceList;
    private List<String> removeLabelKeys;
    private List<String> removeAnnotationKeys;

    NamespaceOperationRegion(String op, CoreV1Api coreV1Api) {
        this.op = op;
        this.coreV1Api = coreV1Api;
        this.namespace = new Namespace();
    }

    public NamespaceOperationRegion withName(String name) {
        verifyKubeResourceName(name);
        this.namespace.setName(name);
        return this;
    }

    public NamespaceOperationRegion withNamespace(Namespace namespace) {
        verifyKubeResourceName(namespace.getName());
        this.namespace = namespace;
        return this;
    }

    public NamespaceOperationRegion withLabels(Map<String, String> labels) {
        this.namespace.setLabels(labels);
        return this;
    }

    public NamespaceOperationRegion addLabel(String labelKey, String labelValue) {
        if (this.namespace.getLabels() == null) {
            this.namespace.setLabels(new HashMap<>(4));
        }
        this.namespace.getLabels().put(labelKey, labelValue);
        return this;
    }

    public NamespaceOperationRegion removeLabel(String labelKey) {
        if (this.removeLabelKeys == null) {
            this.removeLabelKeys = new ArrayList<>(4);
        }
        this.removeLabelKeys.add(labelKey);
        return this;
    }

    public NamespaceOperationRegion withAnnotations(Map<String, String> annotations) {
        this.namespace.setAnnotations(annotations);
        return this;
    }

    public NamespaceOperationRegion addAnnotation(String annotationKey, String annotationValue) {
        if (this.namespace.getAnnotations() == null) {
            this.namespace.setAnnotations(new HashMap<>(4));
        }
        this.namespace.getAnnotations().put(annotationKey, annotationValue);
        return this;
    }

    public NamespaceOperationRegion removeAnnotation(String annotationKey) {
        if (this.removeAnnotationKeys == null) {
            this.removeAnnotationKeys = new ArrayList<>(4);
        }
        this.removeAnnotationKeys.add(annotationKey);
        return this;
    }

    public NamespaceOperationRegion withLabels(String labels) {
        return this.withLabels(StringUtil.stringToMap(labels));

    }

    public NamespaceOperationRegion withAnnotations(String annotations) {
        return this.withAnnotations(StringUtil.stringToMap(annotations));
    }

    public String doneReturnYamlStr() throws Exception {
        doneVoid();
        return this.returnValue;
    }

    public List<Namespace> doneReturnList() throws Exception {
        doneVoid();
        return this.namespaceList;
    }

    public void doneVoid() throws Exception {
        opRegion(this, op);
    }

    private void create() throws ApiException {
        V1Namespace v1Namespace = ObjectTranslateUtil.platFormToKube(this.namespace);
        coreV1Api.createNamespace(v1Namespace, null, null, null);
        this.returnValue = Yaml.dump(v1Namespace);
    }

    private void delete() throws ApiException {
        //这里有一个错误，删除操作返回结果本来是整个资源数据转json，但是传的类型却是V1Status，
        // 无法解析Json，这个问题github有提到，可能都会存在整个操作返回结果json解析错误的问题(不确定)
        // 但是不影响操作结果，仍然成功！

        if (this.namespace.getName() != null) {
            try {
                coreV1Api.deleteNamespace(this.namespace.getName(), null, null, null, null, false, null);
            } catch (JsonSyntaxException e) {
                log.warn("JSON解析错误，为API错误，暂时忽略:{}", e.getMessage());
            }
        }

        //若资源标签为默认标签，则不需操作
        if (StringUtil.compareMap(this.namespace.getLabels(),StringUtil.stringToMap(ProgramInformationConstant.PROGRAM_DEFAULT_LABELS))) {
            return;
        }
        list();
        for (Namespace namespaceItem : this.namespaceList) {
            if (namespaceItem.getName().equals(this.namespace.getName())) {
                continue;
            }
            try {
                coreV1Api.deleteNamespace(namespaceItem.getName(), null, null, null, null, false, null);
            } catch (JsonSyntaxException e) {
                log.warn("JSON解析错误，为API错误，暂时忽略:{}", e.getMessage());
            }
        }

    }

    private void get() throws ApiException {
        if (this.namespace.getName() == null || this.namespace.getName().isEmpty()) {
            throw new KubeResourceNameRequiredException();
        }
        V1Namespace v1Namespace = coreV1Api.readNamespace(this.namespace.getName(), null, null, null);
        this.returnValue = Yaml.dump(v1Namespace);
    }

    private void list() throws ApiException {
        List<Namespace> namespaces = new ArrayList<>();
        String labelStr = StringUtil.mapToString(this.namespace.getLabels());
        V1NamespaceList v1NamespaceList = coreV1Api.listNamespace(null, null, null, labelStr, null, null, null, false);
        for (V1Namespace v1Namespace : v1NamespaceList.getItems()) {
            Namespace namespace = ObjectTranslateUtil.kubeToPlatForm(v1Namespace);
            namespaces.add(namespace);
        }
        this.namespaceList = namespaces;
        this.returnValue = Yaml.dump(namespaces);
    }

    private void edit() throws ApiException {
        get();
        final String labelPath = "/metadata/labels";
        final String annotationPath = "/metadata/annotations";
        List<HttpPatch> httpPatches = new ArrayList<>();
        Namespace namespace = ObjectTranslateUtil.kubeToPlatForm(Yaml.loadAs(this.returnValue, V1Namespace.class));
        if (namespace.getLabels() == null && this.namespace.getLabels() != null) {
            namespace.setLabels(this.namespace.getLabels());
            httpPatches.add(HttpPatch.newAddOp(labelPath, new HashMap<>(1)));
        } else if (namespace.getLabels() != null && this.namespace.getLabels() != null) {
            namespace.getLabels().putAll(this.namespace.getLabels());
        }

        if (namespace.getAnnotations() == null && this.namespace.getAnnotations() != null) {
            namespace.setAnnotations(this.namespace.getAnnotations());
            httpPatches.add(HttpPatch.newAddOp(annotationPath, new HashMap<>(1)));
        } else if (namespace.getAnnotations() != null && this.namespace.getAnnotations() != null) {
            namespace.getAnnotations().putAll(this.namespace.getAnnotations());
        }
        for (Map.Entry<String, String> entry : namespace.getLabels().entrySet()) {
            httpPatches.add(HttpPatch.newAddOp(labelPath + "/" + entry.getKey(), entry.getValue()));
        }
        for (Map.Entry<String, String> entry : namespace.getAnnotations().entrySet()) {
            httpPatches.add(HttpPatch.newAddOp(annotationPath + "/" + entry.getKey(), entry.getValue()));
        }
        if (this.removeLabelKeys != null) {
            for (String key : this.removeLabelKeys) {
                if (namespace.getLabels().containsKey(key)) {
                    httpPatches.add(HttpPatch.newRemoveOp(labelPath + "/" + key));
                }
            }
        }
        if (this.removeAnnotationKeys != null) {
            for (String key : this.removeAnnotationKeys) {
                if (namespace.getAnnotations().containsKey(key)) {
                    httpPatches.add(HttpPatch.newRemoveOp(annotationPath + "/" + key));
                }
            }
        }
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String httpPatchesStr = gson.toJson(httpPatches);
        io.kubernetes.client.custom.V1Patch v1Patch = new io.kubernetes.client.custom.V1Patch(httpPatchesStr);
        V1Namespace v1Namespace = coreV1Api.patchNamespace(namespace.getName(), v1Patch, null, null, null, null);
        this.returnValue = Yaml.dump(v1Namespace);
    }
}
