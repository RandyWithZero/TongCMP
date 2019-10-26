package com.tongtech.cmp.kube.util;

import com.tongtech.cmp.kube.model.ConfigMap;
import com.tongtech.cmp.kube.model.Namespace;
import io.kubernetes.client.models.V1ConfigMap;
import io.kubernetes.client.models.V1Namespace;
import io.kubernetes.client.models.V1ObjectMeta;

/**
 * description 平台资源和kubernetes资源转换，数据复制
 * <p>
 * version 0.1
 * createDate 2019/10/23 17:32
 * updateDate 2019/10/23 17:32
 *
 * @author wangshaoqi
 */
public final class ObjectTranslateUtil {
    private ObjectTranslateUtil() {
    }

    public static ConfigMap kubeToPlatForm(V1ConfigMap v1ConfigMap) {
        V1ObjectMeta metadata = v1ConfigMap.getMetadata();
        ConfigMap configMap = new ConfigMap(metadata.getName(), metadata.getNamespace());
        configMap.setData(v1ConfigMap.getData());
        configMap.setLabels(metadata.getLabels());
        configMap.setAnnotations(metadata.getAnnotations());
        return configMap;
    }

    public static V1ConfigMap platFormToKube(ConfigMap configMap) {
        V1ConfigMap v1ConfigMap = new V1ConfigMap();
        V1ObjectMeta metadata = new V1ObjectMeta();
        metadata.setNamespace(configMap.getNamespace());
        metadata.setName(configMap.getName());
        v1ConfigMap.setData(configMap.getData());
        metadata.setLabels(configMap.getLabels());
        metadata.setAnnotations(configMap.getAnnotations());
        v1ConfigMap.setKind("ConfigMap");
        v1ConfigMap.setApiVersion("v1");
        v1ConfigMap.setMetadata(metadata);
        return v1ConfigMap;
    }

    public static Namespace kubeToPlatForm(V1Namespace v1Namespace) {
        V1ObjectMeta metadata = v1Namespace.getMetadata();
        Namespace namespace = new Namespace(metadata.getName());
        namespace.setLabels(metadata.getLabels());
        namespace.setAnnotations(metadata.getAnnotations());
        return namespace;
    }

    public static V1Namespace platFormToKube(Namespace namespace) {
        V1Namespace v1Namespace = new V1Namespace();
        V1ObjectMeta metadata = new V1ObjectMeta();
        metadata.setName(namespace.getName());
        metadata.setLabels(namespace.getLabels());
        metadata.setAnnotations(namespace.getAnnotations());
        v1Namespace.setKind("Namespace");
        v1Namespace.setApiVersion("v1");
        v1Namespace.setMetadata(metadata);
        return v1Namespace;
    }
}
