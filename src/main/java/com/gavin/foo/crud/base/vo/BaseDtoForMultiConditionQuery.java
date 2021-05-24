package com.gavin.foo.crud.base.vo;

import lombok.Data;

/**
 *
 * @Author Gavin
 * @Description 多条件value匹配基础类
 * @Date 2021/4/7 11:11
 **/
@Data
public class BaseDtoForMultiConditionQuery extends BaseDtoForQuery{

	private String[] fuzzyMulti;

}
