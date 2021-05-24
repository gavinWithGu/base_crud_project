package com.gavin.foo.crud.bean.vo;

import java.io.IOException;

import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Dto - 添加产品
 *
 * @author lilh
 * @date 2017/7/19 19:18
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class UserDtoForAdd {

	/**
     * 用户名称
     */
	@NotNull(message = "name不能为空")
    @ApiModelProperty(value = "用户名", required = true)
    private String name;

    /**
     * email
     */
    @NotNull(message = "email不能为空")
    @ApiModelProperty(value = "email", required = true)
    private String email;

    public UserDtoForAdd(String json) throws IOException {
    	UserDtoForAdd user = new ObjectMapper().readValue(json, UserDtoForAdd.class);
    	BeanUtils.copyProperties(user, this);
    }
}
