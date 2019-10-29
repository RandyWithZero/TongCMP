package com.tongtech.cmp.exception;

import com.tongtech.cmp.common.exception.TongCmpException;

/**
 * description
 * <p>
 * version 0.1
 * createDate 2019/10/29 16:21
 * updateDate 2019/10/29 16:21
 *
 * @author wangshaoqi
 */
public class CustomServiceException extends TongCmpException {
    public CustomServiceException(String message) {
        super(message);
    }
}
