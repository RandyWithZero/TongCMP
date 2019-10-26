package com.tongtech.cmp.kube;

import com.tongtech.cmp.kube.model.Namespace;
import com.tongtech.cmp.kube.util.ObjectTranslateUtil;
import com.tongtech.cmp.kube.util.StringUtil;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Namespace;
import io.kubernetes.client.models.V1ObjectMeta;
import io.kubernetes.client.util.Yaml;
import lombok.Setter;

import java.util.HashMap;
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
@Setter
public class NamespaceOperationRegion extends OperationRegion {
    private String op;
    private Map<String, String> labels;
    private Map<String, String> annotations;
    private CoreV1Api coreV1Api;
    private String name;
    private V1Namespace v1Namespace;

    public NamespaceOperationRegion withName(String name) {
        this.name = name;
        return this;
    }

    public NamespaceOperationRegion withNamespace(Namespace namespace) {
        this.name=namespace.getName();
        this.labels=namespace.getLabels();
        this.annotations=namespace.getAnnotations();
        return this;
    }

    public NamespaceOperationRegion withLabels(Map<String, String> labels) {
        this.labels = labels;
        return this;
    }

    public NamespaceOperationRegion addLabel(String labelKey, String labelValue) {
        if (this.labels == null) {
            this.labels = new HashMap<>(4);
        }
        this.labels.put(labelKey, labelValue);
        return this;
    }

    public NamespaceOperationRegion withAnnotations(Map<String, String> annotations) {
        this.annotations = annotations;
        return this;
    }

    public NamespaceOperationRegion addAnnotation(String annotationKey, String annotationValue) {
        if (this.annotations == null) {
            this.annotations = new HashMap<>(4);
        }
        this.annotations.put(annotationKey, annotationValue);
        return this;
    }

    public NamespaceOperationRegion withLabels(String labels) {
        this.labels = StringUtil.stringToMap(labels);
        return this;
    }

    public NamespaceOperationRegion withAnnotations(String annotations) {
        this.annotations = StringUtil.stringToMap(annotations);
        return this;
    }

    public void done() throws ApiException {
        switch (op) {
            case OP_CREATE:
                try {
                    create();
                } catch (ApiException e) {
                    System.out.println(e.getResponseBody());
                }
                break;
            default:

        }
    }


    private void create() throws ApiException {
        if (this.v1Namespace == null) {
            this.v1Namespace = new V1Namespace();
            V1ObjectMeta v1ObjectMeta = new V1ObjectMeta();
            this.v1Namespace.setMetadata(v1ObjectMeta);
        }
        V1ObjectMeta metadata = this.v1Namespace.getMetadata();
        metadata.setName(name);
        metadata.setLabels(labels);
        metadata.setAnnotations(annotations);
        this.v1Namespace.setApiVersion("v1");
        this.v1Namespace.setKind("Namespace");
        System.out.println(Yaml.dump(this.v1Namespace));
        coreV1Api.createNamespace(this.v1Namespace, null, null, null);
    }
}
