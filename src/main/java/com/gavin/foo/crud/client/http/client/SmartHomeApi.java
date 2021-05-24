package com.gavin.foo.crud.client.http.client;

import com.gavin.foo.crud.client.http.pojo.ResponseBeanObject;
import com.gavin.foo.crud.client.http.pojo.RespDataHome;
import com.gavin.foo.crud.client.http.pojo.RespDataUser;
import com.gavin.foo.crud.client.http.pojo.ResponseBeanArray;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SmartHomeApi {
	@POST("smart_home/login/pwd")
	@Headers({ "Content-Type: application/json",
			"User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36" })
	Call<ResponseBeanObject<RespDataUser>> loginPwd(@Body RequestBody requestBody);

	
	@GET("smart_home/homes")
	@Headers({ "Content-Type: application/json",
			"User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36" })
	Call<ResponseBeanArray<RespDataHome>> homeList(@Header("Authorization") String authorization);
}
