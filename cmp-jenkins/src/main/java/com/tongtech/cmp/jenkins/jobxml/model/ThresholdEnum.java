package com.tongtech.cmp.jenkins.jobxml.model;

import lombok.Getter;

/**
 * description 构建jenkins 参数properties中的其他工程构建后触发：构建状态要求枚举
 * <p>
 * version 0.1
 * createDate 2019/10/14 18:00
 * updateDate 2019/10/14 18:00
 *
 * @author wangshaoqi
 */
@Getter
public enum ThresholdEnum {
    /**
     * 只有构建稳定触发
     */
    SUCCESS("SUCCESS", 0, "BLUE", true), /**
     * 即使构建不稳定触发
     */
    UNSTABLE("UNSTABLE", 1, "YELLOW", true), /**
     * 即使构建失败触发
     */
    FAILURE("FAILURE", 2, "RED", true);
    private String name;
    private int ordinal;
    private String color;
    private boolean completeBuild;

    ThresholdEnum(String name, int ordinal, String color, boolean completeBuild) {
        this.name = name;
        this.color = color;
        this.ordinal = ordinal;
        this.completeBuild = completeBuild;
    }
}
