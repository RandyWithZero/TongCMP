package com.tongtech.cmp.kube.model;

/**
 * description k8s Resource：Namespace
 * <p>
 * version 0.1
 * createDate 2019/10/18 16:18
 * updateDate 2019/10/18 16:18
 *
 * @author wangshaoqi
 */
public class Namespace extends ProjectKubeResourceBase {
    public Namespace(String name) {
        this.name = name;
    }
}
