package com.tongtech.cmp.kube.model;

import java.util.List;
import java.util.Map;

/**
 *
 *description 
 *
 *version 0.1
 *createDate 2019/10/30 9:23
 *updateDate 2019/10/30 9:23
 *@author wangshaoqi
 */
public class Container {
    private String image;
    private String imagePullPolicy;
    private String name;
    private List<Port> portList;
    private List<VolumeMount> volumeMountList;
    private String command;
    private String commandArgs;
    private Map<String,String> envList;
    private String cpuLimit;
    private String cpuRequest;
    private String memoryLimit;
    private String memoryRequest;
    private String runAsUser;
    private String runAsGroup;
    private String fsGroup;

    public Container() {
    }

}
