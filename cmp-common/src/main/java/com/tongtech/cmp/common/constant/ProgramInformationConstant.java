package com.tongtech.cmp.common.constant;

/**
 *
 *description 一些描述项目的常量信息
 *
 *version 0.1
 *createDate 2019/10/21 10:10
 *updateDate 2019/10/21 10:10
 *@author wangshaoqi
 */
public final  class ProgramInformationConstant {
    private ProgramInformationConstant(){

    }
    public static final String PROGRAM_NAME="TongCMP";
    public static final String PROGRAM_VERSION="1.0";
    /**
     * 平台部署的应用默认含义的标签 多个标签用','隔开
     */
    public static final String PROGRAM_DEFAULT_LABELS="origin=tongcmp,version="+PROGRAM_VERSION;
    /**
     * 平台部署的应用默认含义的注解 多个注解用','隔开
     */
    public static final String PROGRAM_DEFAUTL_ANNOTATIONS="official.website=http://www.tongtech.com/";
}
