package com.gavin.foo.crud.controller;

import javax.servlet.http.HttpServletRequest;

import com.gavin.foo.crud.base.constants.SystemOperateType;
import com.gavin.foo.crud.base.publish.CommonChangeEvent;
import com.gavin.foo.crud.base.publish.CommonEventPublisherUtils;
import com.gavin.foo.crud.base.vo.BaseDtoForQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseController<T extends BaseDtoForQuery> {
	// log日志记录器
	private static final Logger log = LoggerFactory.getLogger(BaseController.class);

	@Autowired
	private HttpServletRequest request;

	/**
	 * 提供异步监听方法，完成操作记录数据库操作
	 *
	 * @date: 2021/4/8 10:36
	 * @param content
	 * @param operateType
	 * @return:
	 */
	public void publish(String content,
			SystemOperateType operateType) {
		
		CommonChangeEvent.Builder builder = CommonChangeEvent.Builder.create();
		builder.setContent(content);
		builder.setIp(this.getClientIp());
		builder.setOperator(this.getOperator());
		builder.setType(operateType);
		CommonEventPublisherUtils.publishEvent(builder.build());
	}
	
	/**
	 * 获取操作者
	 * @return
	 */
	protected String getOperator(){
		//todo
		return "admin";
	}
	
	protected String getClientIp() {
		String remoteAddr = "";
		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}

			log.trace("Client ip:{}", remoteAddr);
		}
		return remoteAddr;
	}
}
