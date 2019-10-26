package com.tongtech.cmp.jenkins.jobxml.model;

import lombok.Data;

import javax.xml.bind.annotation.*;

/**
 * description 构建jenkins 参数properties中的gitLab提交触发
 * <p>
 * version 0.1
 * createDate 2019/10/16 10:01
 * updateDate 2019/10/16 10:01
 *
 * @author wangshaoqi
 */
@Data
@XmlRootElement(name = "com.dabsquared.gitlabjenkins.GitLabPushTrigger")
@XmlAccessorType(XmlAccessType.NONE)
public class GitLabPushTrigger {
    @XmlElement(name = "spec")
    private String spec;
    @XmlElement(name = "triggerOnPush")
    private boolean triggerOnPush = true;
    @XmlElement(name = "triggerOnMergeRequest")
    private boolean triggerOnMergeRequest = true;
    @XmlElement(name = "triggerOnPipelineEvent")
    private boolean triggerOnPipelineEvent;
    @XmlElement(name = "triggerOnAcceptedMergeRequest")
    private boolean triggerOnAcceptedMergeRequest;
    @XmlElement(name = "triggerOnClosedMergeRequest")
    private boolean triggerOnClosedMergeRequest;
    @XmlElement(name = "triggerOnApprovedMergeRequest")
    private boolean triggerOnApprovedMergeRequest = true;
    @XmlElement(name = "triggerOpenMergeRequestOnPush")
    private String triggerOpenMergeRequestOnPush = "never";
    @XmlElement(name = "triggerOnNoteRequest")
    private boolean triggerOnNoteRequest = true;
    @XmlElement(name = "noteRegex")
    private String noteRegex;
    @XmlElement(name = "ciSkip")
    private boolean ciSkip = true;
    @XmlElement(name = "skipWorkInProgressMergeRequest")
    private boolean skipWorkInProgressMergeRequest = true;
    @XmlElement(name = "setBuildDescription")
    private boolean setBuildDescription = true;
    @XmlElement(name = "branchFilterType")
    private String branchFilterType = "All";
    @XmlElement(name = "includeBranchesSpec")
    private String includeBranchesSpec;
    @XmlElement(name = "excludeBranchesSpec")
    private String excludeBranchesSpec;
    @XmlElement(name = "sourceBranchRegex")
    private String sourceBranchRegex;
    @XmlElement(name = "targetBranchRegex")
    private String targetBranchRegex;
    @XmlElement(name = "secretToken")
    private String secretToken="db221a99f2638753abdbaabac07b34bf";
    @XmlElement(name = "pendingBuildName")
    private String pendingBuildName;
    @XmlElement(name = "cancelPendingBuildsOnUpdate")
    private boolean cancelPendingBuildsOnUpdate;
    @XmlAttribute(name = "plugin")
    private String plugin;
    private final String pluginName="gitlab-plugin";
}
