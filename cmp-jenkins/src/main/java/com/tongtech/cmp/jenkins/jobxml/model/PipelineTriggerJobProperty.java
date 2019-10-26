package com.tongtech.cmp.jenkins.jobxml.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * description 构建jenkins jobXML PipelineTriggerJobProperty部分
 * <p>
 * version 0.1
 * createDate 2019/10/14 18:13
 * updateDate 2019/10/14 18:13
 *
 * @author wangshaoqi
 */
@XmlRootElement(name = "org.jenkinsci.plugins.workflow.job.properties.PipelineTriggersJobProperty")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class PipelineTriggerJobProperty {
    @XmlElement(name = "triggers")
    private TriggerGroup triggerGroup;
}
