package com.gavin.foo.crud.base.web;

import com.gavin.foo.crud.exception.SystemExceptionEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by rongmc on 16/3/22.
 */
public class ResponseObject<T> {

    public final static String NORMAL = "200";

    @ApiModelProperty("接口请求返回状态码：默认成功 200")
    private String code = NORMAL;

    @ApiModelProperty("请求结果，简短描述：如 本次请求成功!")
    private String message = "本次请求成功!";

    @ApiModelProperty("数据返回结果，具体请参考返回值对象")
    private T data = null;

    @ApiModelProperty("显示字段")
    private String[] display = null;

    public ResponseObject() {
    }

    public ResponseObject(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseObject(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseObject(T data) {
        this.data = data;
    }

    public static <T> ResponseObject<T> response(SystemExceptionEnum sysExceptionEnum) {
        return new ResponseObject(sysExceptionEnum.getCode(), sysExceptionEnum.getMessage());
    }

    public static <T> ResponseObject<T> response(SystemExceptionEnum sysExceptionEnum, T data) {
        return new ResponseObject(sysExceptionEnum.getCode(), sysExceptionEnum.getMessage(), data);
    }

    public static <T> ResponseObject<T> response(String code, String msg) {
        return new ResponseObject<>(code, msg);
    }

    public static <T> ResponseObject<T> ok(T data) {
        return new ResponseObject<T>("200", "本次请求成功", data);
    }
    
    public static <T> ResponseObject<T> ok() {
        return new ResponseObject<T>("200", "本次请求成功");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String[] getDisplay() {
        return display;
    }

    public void setDisplay(String[] display) {
        this.display = display;
    }
}
