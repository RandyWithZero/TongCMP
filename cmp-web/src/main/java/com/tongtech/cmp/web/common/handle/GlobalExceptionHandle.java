package com.tongtech.cmp.web.common.handle;

import com.tongtech.cmp.web.common.domain.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * description 全局异常处理
 * <p>
 * version 0.1
 * createDate 2019/4/16 15:18
 * updateDate 2019/4/16 15:18
 *
 * @author wangshaoqi
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandle {
    @ResponseBody
    @ExceptionHandler
    public ResponseWrapper exceptionHandle(Exception e) {
        log.warn(">>>>>异常信息：{}" , e.getMessage());
        e.printStackTrace();
        return new ResponseWrapper().error(e.getMessage());
    }
}
