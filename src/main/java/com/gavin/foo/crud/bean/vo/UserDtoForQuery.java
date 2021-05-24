package com.gavin.foo.crud.bean.vo;

import com.gavin.foo.crud.base.vo.BaseDtoForQuery;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by zhl on 2018/1/11.
 */
@Data
@Accessors(chain = true)
public class UserDtoForQuery extends BaseDtoForQuery{

    private String name;

    private String email;
}
