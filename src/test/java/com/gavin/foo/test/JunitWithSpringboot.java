package com.gavin.foo.test;

import com.gavin.foo.crud.MainApplication;
import com.gavin.foo.crud.base.web.RequestObject;
import com.gavin.foo.crud.base.web.ResponseObject;
import com.gavin.foo.crud.bean.vo.RedisObject;
import com.gavin.foo.crud.controller.SampleController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class JunitWithSpringboot {
	
	@Resource
	private SampleController controller;

	@Test
	public void devReg() {

		RequestObject<String> requestObject = new RequestObject<String>();
		requestObject.setData("aaa");

		RequestObject<RedisObject> body = new RequestObject<RedisObject>();
		RedisObject object = new RedisObject();
		object.setKey("aaa");
		object.setValue("abc");
		body.setData(object);
		controller.redisset(body);


		ResponseObject result = controller.redisget(requestObject);
		Assert.assertNotNull(result);
	}
	
}
