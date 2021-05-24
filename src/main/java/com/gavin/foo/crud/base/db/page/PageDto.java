/**
 * Copyright (c) 2011-2014, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.gavin.foo.crud.base.db.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

/**
 * 分页查询对象
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PageDto<T>  {

    @Valid
    private T query;

    @ApiModelProperty(value = "每页记录数，默认 10",example = "10")
    private int size = 10;

    @ApiModelProperty(value = "当前页",example = "1")
    private int current = 1;



}
