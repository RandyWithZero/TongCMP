package com.tongtech.cmp.kube.model;

import com.tongtech.cmp.common.constant.ProgramInformationConstant;
import com.tongtech.cmp.common.constant.RegexRuleConstant;
import com.tongtech.cmp.kube.exception.KubeResourceNameNotCorrectException;
import com.tongtech.cmp.kube.util.StringUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description 定义项目中资源的父类
 * <p>
 * version 0.1
 * createDate 2019/10/22 16:27
 * updateDate 2019/10/22 16:27
 *
 * @author wangshaoqi
 */
@Getter
@Setter
@ToString
public class ProjectKubeResourceBase {
    String name;
    Map<String, String> labels;
    Map<String, String> annotations;

    ProjectKubeResourceBase() {
        this.labels = new HashMap<>(4);
        this.annotations = new HashMap<>(4);
        //赋予默认标签
        String programLabel = ProgramInformationConstant.PROGRAM_DEFAULT_LABELS;
        this.labels = StringUtil.stringToMap(programLabel);
        //赋予默认注解
        String programAnnotation = ProgramInformationConstant.PROGRAM_DEFAUTL_ANNOTATIONS;
        this.annotations = StringUtil.stringToMap(programAnnotation);
    }

    public void addLabel(String labelKey, String labelValue) {
        labels.put(labelKey, labelValue);
    }

    public void addAnnotation(String annotationKey, String annotationValue) {
        annotations.put(annotationKey, annotationValue);
    }

    public void addLabelMap(Map<String, String> labels) {
        this.labels.putAll(labels);
    }

    public void addAnnotationMap(Map<String, String> annotations) {
        this.annotations.putAll(annotations);
    }
}
