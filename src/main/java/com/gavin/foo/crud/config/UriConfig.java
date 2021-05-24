package com.gavin.foo.crud.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 测试配置类
 */
@Component
@ConfigurationProperties(prefix = "uri")
@Data
public class UriConfig {
	private String host;
	private String port;
}
