package com.gavin.foo.crud.redis;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component("redisTemplateUtil")
public class RedisTemplateUtil {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Resource(name="redisTemplate")
	private RedisTemplate<String,Object> redisTemplate;

	public Object getObject(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	public void setObject(String key, Object value, long timeout) {
		redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MINUTES);
	}
	
	public String get(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	public void set(String key, String value, long timeout) {
		stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MINUTES);
	}

	public void remove(String key) {
		redisTemplate.delete(key);
	}
	
	public void remove(Collection<String> keyList) {
		redisTemplate.delete(keyList);
	}

}
