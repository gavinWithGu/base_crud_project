package com.gavin.foo.crud.base.service;

import java.util.List;

import com.gavin.foo.crud.base.constants.SystemOperateType;
import com.gavin.foo.crud.base.db.page.PageDto;
import com.gavin.foo.crud.base.db.page.PageVo;
import com.gavin.foo.crud.base.publish.CommonChangeEvent;
import com.gavin.foo.crud.base.publish.CommonEventPublisherUtils;
import com.gavin.foo.crud.base.vo.BaseDtoForMultiConditionQuery;
import com.gavin.foo.crud.base.vo.BaseDtoForQuery;
import com.gavin.foo.crud.log.LogstashFactory;
import com.gavin.foo.crud.log.LogstashLogger;
import com.gavin.foo.crud.log.module.BusinessModule;
import com.gavin.foo.crud.util.GeneralUtils;
import com.gavin.foo.crud.util.WebUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 基礎base,提供可以泛型化得方法
 * 
 * @author Gavin
 *
 * @param <T>
 * @param <M>
 */
@Component
public abstract class BaseService<T, DTO, M extends BaseMapper<T>> extends ServiceImpl<M, T>
		implements IBaseService<T, DTO, BaseMapper<T>> {

	private final static LogstashLogger logger = LogstashFactory.getLogger(BusinessModule.SYS_MODULE);

	/**
	 * 公共方法：提供异步的crud日志记录服务
	 *
	 * @date: 2021/4/7 16:16
	 *
	 * @param user2String
	 * @param operateType
	 * @param ip
	 * @param operator
	 * @return:
	 */
	public void publish(String user2String, SystemOperateType operateType, String ip, String operator) {

		CommonChangeEvent.Builder builder = CommonChangeEvent.Builder.create();
		builder.setContent(user2String);
		builder.setIp(ip);
		builder.setOperator(operator);
		builder.setType(operateType);
		CommonEventPublisherUtils.publishEvent(builder.build());
	}

	/**
	 * 公共分页方法，查询实例：<br/>
	 *  "fuzzyMulti": [
	 *     "mobile:+8617721064018","email:abcd"
 	 *  ]
	 *
	 * @date: 2021/4/7 16:18
	 * @param pageDto : 分页对象，包括"current","size","fuzzyMulti"
	 * @return:
	 * @return com.simon.cloud.iot.ota.base.db.page.PageVo<DTO>
	 */
	public PageVo<DTO> pageFindMultiCondition(PageDto<BaseDtoForMultiConditionQuery> pageDto) {
		logger.info("Page find by multi-condition {}",pageDto);
		Page<T> page = new Page<T>(pageDto.getCurrent(), pageDto.getSize());
		QueryWrapper<T> wrapper = this.generateFuzzyCondition(pageDto.getQuery().getFuzzyMulti());
		return this.pageQuery(page, wrapper);
	}

	private QueryWrapper<T> generateFuzzyCondition(BaseDtoForQuery query, SFunction<T, ?>... column) {
	
		QueryWrapper<T> wrapper = new QueryWrapper<T>();
		if (GeneralUtils.isNotNull(query)) {
			String fuzzy = query.getFuzzy();

			if (StringUtils.isNotBlank(fuzzy)) {
				fuzzy = WebUtils.escapeChar(fuzzy);
				for (SFunction<T, ?> sFunction : column) {
					wrapper.lambda().like(sFunction, fuzzy).or();
				}
			}
		}
		return wrapper;
	}
	
	private QueryWrapper<T> generateFuzzyCondition(String[] cond) {

		QueryWrapper<T> wrapper = new QueryWrapper<T>();
		if (GeneralUtils.isNotNull(cond)) {

			for (String key : cond) {
				String[] item = key.split(":");
				wrapper.like(item[0], item[1]);
			}
		}
		return wrapper;
	}


	private PageVo<DTO> pageQuery(Page<T> page, QueryWrapper<T> wrapper) {
		Page<T> dbPage = this.getBaseMapper().selectPage(page, wrapper);
		PageVo<DTO> pageVo = new PageVo<DTO>();
		BeanUtils.copyProperties(dbPage, pageVo);
		return pageVo;
	}

	/**
	 * 公共分页方法，查询实例：<br/>
	 *  "fuzzy": "abc",
	 *
	 * @date: 2021/4/7 16:21
	 * @param pageDto : 分页对象，包括"current","size","fuzzy"
	 * @param column: 需要匹配的列集合,eg: UserEntity::getEmail, UserEntity::getName
	 * @return:
	 * @return com.simon.cloud.iot.ota.base.db.page.PageVo<DTO>
	 */
	public PageVo<DTO> pageFind(PageDto<BaseDtoForQuery> pageDto, SFunction<T, ?>... column) {

		Page<T> page = new Page<T>(pageDto.getCurrent(), pageDto.getSize());
		QueryWrapper<T> wrapper = this.generateFuzzyCondition(pageDto.getQuery(), column);
		return this.pageQuery(page, wrapper);
	}

	

	/**
	 * 自定义QueryWrapper对象去查询数据
	 * 
	 * @param current
	 * @param wrapper
	 *            查询条件
	 * @return
	 */
	public PageVo<M> pageByQueryWrapper(int current, int size, QueryWrapper<T> wrapper) {

		Page<T> page = new Page<T>(current, size);
		Page<T> dbPage = this.getBaseMapper().selectPage(page, wrapper);
		List<T> records = dbPage.getRecords();

		PageVo<M> pageVo = new PageVo<>();
		if (CollectionUtils.isEmpty(records)) {
			return pageVo;
		}

		BeanUtils.copyProperties(dbPage, pageVo);
		return pageVo;
	}

}
