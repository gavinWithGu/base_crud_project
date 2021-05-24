package com.gavin.foo.crud.client.http.pojo;

import lombok.Data;

@Data
public class ResponseBeanObject<T extends BaseResponseData> extends BaseResponseBean{
	private T data;
}
