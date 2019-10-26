package com.tongtech.cmp.jenkins.jobxml.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * description 构建jenkins 参数properties中的触发器集
 * <p>
 * version 0.1
 * createDate 2019/10/15 10:21
 * updateDate 2019/10/15 10:21
 *
 * @author wangshaoqi
 */
@XmlRootElement(name = "triggers")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class TriggerGroup {
    @XmlElement(name = "com.cloudbees.jenkins.GitHubPushTrigger")
    private GitHubPushTrigger gitHubPushTrigger;
    @XmlElement(name = "hudson.triggers.TimerTrigger")
    private TimerTrigger timerTrigger;
    @XmlElement(name = "jenkins.triggers.ReverseBuildTrigger")
    private ReverseBuildTrigger reverseBuildTrigger;
    @XmlElement(name = "com.dabsquared.gitlabjenkins.GitLabPushTrigger")
    private GitLabPushTrigger gitLabPushTrigger;
}
