package com.gavin.foo.crud.base.web;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;

/**
 * Created by rongmc on 16/3/22.
 */
public class RequestObject<T> {


    @NotBlank(message = "version值不能为空")
    @ApiModelProperty(value = "接口版本,暂时传入 0.1", example = "0.1")
    private String version;

    @Valid
    @ApiModelProperty(value = "具体内容查看模型定义")
    private T data;


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
