package com.tongtech.cmp.web.common.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * description 请求响应体
 * <p>
 * version 0.1
 * createDate 2019/4/16 9:56
 * updateDate 2019/4/16 9:56
 *
 * @author wangshaoqi
 */
@Getter
@Setter
@ToString
@ApiModel
public class ResponseWrapper<T> {

    /**
     * 状态
     */
    @ApiModelProperty(name = "status", value = "响应状态", required = true, dataType = "String")
    private String status;
    /**
     * 响应码
     */
    @ApiModelProperty(name = "code", value = "响应码（0：成功 1：失败）", required = true, dataType = "String")
    private String code;
    /**
     * 响应信息
     */
    @ApiModelProperty(name = "message", value = "响应信息", required = true, dataType = "String")
    private String message;
    /**
     * 传输数据
     */
    @ApiModelProperty(name = "data", value = "响应数据", dataType = "Object")
    private T data;

    /**
     * 无参构造器
     */
    public ResponseWrapper() {
    }

    /**
     * @param status   状态
     * @param infoCode 响应码
     * @param info     响应信息
     * @param data     传输数据
     */
    public ResponseWrapper(String status, String infoCode, String info, T data) {
        this.status = status;
        this.code = infoCode;
        this.message = info;
        this.data = data;
    }

    /**
     * 状态为成功的响应.
     */
    public ResponseWrapper<T> success() {
        return new ResponseWrapper<>(StatusEnum.SUCCESS.getStatus(), StatusEnum.SUCCESS.getCode(), null, null);
    }

    public ResponseWrapper<T> success(String info) {
        ResponseWrapper<T> success = this.success();
        success.setMessage(info);
        return success;
    }

    public ResponseWrapper<T> success(T data) {
        ResponseWrapper<T> success = this.success();
        success.setData(data);
        return success;
    }

    public ResponseWrapper<T> success(String info, T data) {
        ResponseWrapper<T> success = this.success();
        success.setData(data);
        success.setMessage(info);
        return success;
    }

    /**
     * 状态为异常的响应.
     */
    public ResponseWrapper<T> error() {
        return new ResponseWrapper<>(StatusEnum.ERROR.getStatus(), StatusEnum.ERROR.getCode(), null, null);
    }

    public ResponseWrapper<T> error(String info) {
        ResponseWrapper<T> error = this.error();
        error.setMessage(info);
        return error;
    }

    /**
     * 状态类型
     */
    @Getter
    private enum StatusEnum {
        /* 成功 */
        SUCCESS("SUCCESS", "0"),
        /* 异常 */
        ERROR("ERROR", "1");
        private String status;
        private String code;

        StatusEnum(String status, String code) {
            this.status = status;
            this.code = code;
        }
    }
}


