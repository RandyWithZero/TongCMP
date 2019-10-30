package com.tongtech.cmp.kube.model;

import java.util.List;

/**
 *
 *description 
 *
 *version 0.1
 *createDate 2019/10/30 10:31
 *updateDate 2019/10/30 10:31
 *@author wangshaoqi
 */
public class  Pod extends ProjectKubeResourceBase{
    private List<Container> containerList;
    private List<Container> initContainerList;
    private List<Volume> volumeList;
}
