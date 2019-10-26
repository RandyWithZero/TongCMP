package com.tongtech.cmp.common.exception;

/**
 * description CMP项目所有异常的父类
 * <p>
 * version 0.1
 * createDate 2019/10/18 11:06
 * updateDate 2019/10/18 11:06
 *
 * @author wangshaoqi
 */
public class TongCmpException extends RuntimeException {
    public TongCmpException(String message) {
        super(message);
    }
    public TongCmpException(){
        super();
    }
}
