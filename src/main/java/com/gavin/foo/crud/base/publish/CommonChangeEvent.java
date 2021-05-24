package com.gavin.foo.crud.base.publish;

import com.gavin.foo.crud.base.constants.SystemOperateType;
import org.springframework.context.ApplicationEvent;

import lombok.Data;

/**
 * Event - 数据修改事件
 *
 * @author lilh
 * @date 2017/7/20 17:00
 */
@Data
public class CommonChangeEvent extends ApplicationEvent {
	private String content;

	private String operator;

	private String ip;

	private SystemOperateType type;

	private CommonChangeEvent(Object source) {
		super(source);
	}

	public CommonChangeEvent(String content, String operator, String ip, SystemOperateType type) {
		super(new Object());
		this.content = content;
		this.operator = operator;
		this.ip = ip;
		this.type = type;
	}

	@Data
	public static class Builder {
		private String content;

		private String operator;

		private String ip;

		private SystemOperateType type;

		private Builder() {
		}

		public static Builder create() {
			return new Builder();
		}

		public CommonChangeEvent build() {
			return new CommonChangeEvent(this.content, this.operator, this.ip, this.type);
		}
	}
}
