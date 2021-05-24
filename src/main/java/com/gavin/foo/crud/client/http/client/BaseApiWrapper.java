package com.gavin.foo.crud.client.http.client;

import com.gavin.foo.crud.client.http.pojo.BaseResponseBean;
import com.gavin.foo.crud.exception.ExceptionWrapper;
import com.gavin.foo.crud.exception.SystemException;
import com.gavin.foo.crud.exception.SystemExceptionEnum;

public class BaseApiWrapper {
	protected <T extends BaseResponseBean> void checkResp(T t) {
		if (!"200".equals(t.getCode())) {
			ExceptionWrapper.throwSystemException("Http Server return no 200 error! ",SystemExceptionEnum.INTERNAL_ERROR);
		}
	}
}
