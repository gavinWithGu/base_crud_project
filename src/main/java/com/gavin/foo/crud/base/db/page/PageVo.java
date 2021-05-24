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

import java.util.Collections;
import java.util.List;

/**
 * 分页传输对象
 */
@Getter
@Setter
@NoArgsConstructor
public class PageVo<T>  {

    @ApiModelProperty("查询数据列表")
    private List<T> records = Collections.emptyList();

    @ApiModelProperty("总数")
    private long total = 0;

    @ApiModelProperty("每页显示条数，默认 10")
    private long size = 10;

    @ApiModelProperty("总页数")
    private long pages = 0;

    @ApiModelProperty("当前页")
    private long current = 1;


    public PageVo(int current, int size, int total) {
        this.total = total;
        this.size = size;
        this.current = current;
        if(total > 0){
            int remainder = total % size == 0 ? 0 : 1;
            this.pages = total/size + remainder;
        }
    }

    public PageVo( int current, int size, long total, long pages) {
        this.total = total;
        this.size = size;
        this.pages = pages;
        this.current = current;
    }
}
