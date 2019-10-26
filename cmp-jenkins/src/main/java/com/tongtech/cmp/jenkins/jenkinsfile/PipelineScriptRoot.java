package com.tongtech.cmp.jenkins.jenkinsfile;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 *
 *description 
 *
 *version 0.1
 *createDate 2019/10/17 11:02
 *updateDate 2019/10/17 11:02
 *@author wangshaoqi
 */
@Data
public class PipelineScriptRoot {
    private String agent;
    private List<PipelineStage> stageList;
    private Map<String,String> globalEnvList;
}
