package com.tongtech.cmp.kube.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.util.List;
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
public class Deployment extends ProjectKubeResourceBase {
    private String namespace="default";
    private int replicas;
    private List<Pod> podList;
    private Map<String,String> selector;
    public Deployment() {
    }
    public Deployment(String name, String namespace,int replicas) {
        this.name = name;
        this.replicas=replicas;
        if(namespace!=null) {
            this.namespace =  namespace;
        }
    }
    public Deployment(String name, String namespace,int replicas,List<Pod> pods) {
         this(name, namespace,replicas);
         this.podList=pods;
    }
}
