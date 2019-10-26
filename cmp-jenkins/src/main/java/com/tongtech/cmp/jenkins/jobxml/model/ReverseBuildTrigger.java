package com.tongtech.cmp.jenkins.jobxml.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * description 构建jenkins 参数properties中的其他工程构建后触发
 * <p>
 * version 0.1
 * createDate 2019/10/14 17:57
 * updateDate 2019/10/14 17:57
 *
 * @author wangshaoqi
 */
@XmlRootElement(name = "jenkins.triggers.ReverseBuildTrigger")
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class ReverseBuildTrigger {
    @XmlElement(name = "spec")
    private String spec;
    @XmlElement(name = "upstreamProjects")
    private String upstreamProjects;
    @XmlElement(name = "threshold")
    private Threshold threshold;
}
