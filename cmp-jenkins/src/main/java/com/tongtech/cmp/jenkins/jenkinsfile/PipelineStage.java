package com.tongtech.cmp.jenkins.jenkinsfile;

import lombok.Data;

/**
 *
 *description 
 *
 *version 0.1
 *createDate 2019/10/17 11:11
 *updateDate 2019/10/17 11:11
 *@author wangshaoqi
 */
@Data
public class PipelineStage {
    private String name;
    private String agent;
    private String steps;
}
