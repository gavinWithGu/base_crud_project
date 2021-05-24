package com.gavin.foo.crud.client.http.pojo;

import java.util.List;

import lombok.Data;

@Data
public class ResponseBeanArray<T extends BaseResponseData> extends BaseResponseBean{
	private List<T> data;
}
