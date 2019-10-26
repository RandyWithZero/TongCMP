package com.tongtech.cmp.jenkins.jobxml.model;

import lombok.Data;

import javax.xml.bind.annotation.*;

/**
 * description 构建jenkins 参数properties中的gitHub提交触发
 * <p>
 * version 0.1
 * createDate 2019/10/14 18:06
 * updateDate 2019/10/14 18:06
 *
 * @author wangshaoqi
 */
@Data
@XmlRootElement(name = "com.cloudbees.jenkins.GitHubPushTrigger")
@XmlAccessorType(XmlAccessType.NONE)
public class GitHubPushTrigger {
    private final String pluginName = "github";
    @XmlAttribute(name = "plugin")
    private String plugin;
    @XmlElement(name = "spec")
    private String spec;
}
