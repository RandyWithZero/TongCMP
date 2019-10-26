package com.tongtech.cmp.jenkins.jobxml.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * description 构建jenkins 参数properties中的其他工程构建后触发所关注工程的构建状态要求
 * <p>
 * version 0.1
 * createDate 2019/10/15 14:28
 * updateDate 2019/10/15 14:28
 *
 * @author wangshaoqi
 */
@XmlRootElement(name = "threshold")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Threshold {
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "ordinal")
    private int ordinal;
    @XmlElement(name = "color")
    private String color;
    @XmlElement(name = "completeBuild")
    private boolean completeBuild;
}
