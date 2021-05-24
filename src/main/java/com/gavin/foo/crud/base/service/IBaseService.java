package com.gavin.foo.crud.base.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gavin.foo.crud.base.db.page.PageDto;
import com.gavin.foo.crud.base.db.page.PageVo;
import com.gavin.foo.crud.base.vo.BaseDtoForMultiConditionQuery;
import com.gavin.foo.crud.base.vo.BaseDtoForQuery;

/**
 * 基礎base,提供可以泛型化得方法
 * @author dev
 *
 * @param <T>
 * @param <M>
 */
public interface IBaseService<T, DTO ,M extends BaseMapper<T>> extends IService<T>{
	 PageVo<DTO> pageFind(PageDto<BaseDtoForQuery> pageDto, SFunction<T, ?>... column);
	 
	 PageVo<DTO> pageFindMultiCondition(PageDto<BaseDtoForMultiConditionQuery> pageDto);
}
