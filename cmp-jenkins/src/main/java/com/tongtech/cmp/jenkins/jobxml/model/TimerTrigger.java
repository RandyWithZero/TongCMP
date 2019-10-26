package com.tongtech.cmp.jenkins.jobxml.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * description  构建jenkins 参数properties中的定时提交触发
 * <p>
 * version 0.1
 * createDate 2019/10/14 18:05
 * updateDate 2019/10/14 18:05
 *
 * @author wangshaoqi
 */
@XmlRootElement(name = "hudson.triggers.TimerTrigger")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class TimerTrigger {
    @XmlElement(name = "spec")
    private String spec;

}
