package com.tongtech.cmp.jenkins.jobxml.model;

import lombok.Data;

import javax.xml.bind.annotation.*;

/**
 * description  构建jenkins jobXML Definition部分
 * <p>
 * version 0.1
 * createDate 2019/10/14 18:19
 * updateDate 2019/10/14 18:19
 *
 * @author wangshaoqi
 */
@XmlRootElement(name = "definition")
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class Definition {
    @XmlAttribute(name = "class")
    private final String clazz = "org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition";
    private final String pluginName = "workflow-cps";
    @XmlAttribute(name = "plugin")
    private String plugin;
    @XmlElement(name = "script")
    private String script;
    @XmlElement(name = "sandbox")
    private boolean sandbox = true;
}
