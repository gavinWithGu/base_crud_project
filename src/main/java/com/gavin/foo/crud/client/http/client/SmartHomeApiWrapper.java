package com.gavin.foo.crud.client.http.client;

import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSONObject;
import com.gavin.foo.crud.client.http.pojo.RespDataHome;
import com.gavin.foo.crud.client.http.pojo.RespDataUser;
import com.gavin.foo.crud.client.http.pojo.ResponseBeanArray;
import com.gavin.foo.crud.client.http.pojo.ResponseBeanObject;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Slf4j
public class SmartHomeApiWrapper extends BaseApiWrapper {
    private volatile static SmartHomeApiWrapper instance;

    private static final String API_URL = "http://192.168.1.219:8090/app/";

    private SmartHomeApi smartHomeApi;

    /**
     * 单例方法，返回被代理的httpclient对象
     *
     * @return com.gavin.foo.crud.client.http.client.SmartHomeApiWrapper
     * @date: 2021/4/7 17:37
     * @param:
     */
    public static SmartHomeApiWrapper singleton() {
        if (null == instance) {
            synchronized (SmartHomeApiWrapper.class) {
                if (null == instance) {
                    instance = new SmartHomeApiWrapper();
                }
            }
        }
        return instance;
    }

    /**
     * 获取 OkHttpClient
     *
     * @return OkHttpClient
     */
    private static OkHttpClient.Builder getClient() {
        return new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS);
    }

    private SmartHomeApiWrapper() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL).client(getClient().build())
                .addConverterFactory(GsonConverterFactory.create()).build();
        smartHomeApi = retrofit.create(SmartHomeApi.class);
    }

    public ResponseBeanObject<RespDataUser> loginPwd(JSONObject req) {

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), req.toString());
        try {
            Response<ResponseBeanObject<RespDataUser>> call = smartHomeApi.loginPwd(requestBody).execute();
            ResponseBeanObject<RespDataUser> body = call.body();

            super.checkResp(body);
            return body;
        } catch (Exception e) {
            log.error("数据获取失败:", e);
        }
        return null;
    }

    public ResponseBeanArray<RespDataHome> homes(String token) {

        try {
            Response<ResponseBeanArray<RespDataHome>> call = smartHomeApi.homeList(token).execute();
            ResponseBeanArray<RespDataHome> body = call.body();

            super.checkResp(body);
            return body;
        } catch (Exception e) {
            log.error("数据获取失败:", e);
        }
        return null;
    }
}
