package com.tongtech.cmp.kube.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * description k8s Resource：ConfigMap
 * <p>
 * version 0.1
 * createDate 2019/10/22 16:20
 * updateDate 2019/10/22 16:20
 *
 * @author wangshaoqi
 */
@Getter
@Setter
@ToString
public class ConfigMap extends ProjectKubeResourceBase {
    private String namespace;
    private Map<String, String> data;

    public ConfigMap(String name, String namespace) {
        this.name = name;
        this.namespace = namespace == null ? "default" : namespace;
    }

    public ConfigMap(String name, String namespace, Map<String, String> data) {
        this(name, namespace);
        this.data = data;
    }
}
