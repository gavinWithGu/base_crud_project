package com.gavin.foo.crud.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author allen.luan@simon.com.cn
 * @Date 2021/1/29
 * @Description：TODO
 **/

@Data
public class BaseRequest {

    @ApiModelProperty(value = "每页记录数，默认 10",example = "10")
    private int size = 10;

    @ApiModelProperty(value = "当前页",example = "1")
    private int current = 1;
}
