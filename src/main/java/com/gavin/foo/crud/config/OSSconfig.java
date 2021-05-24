package com.gavin.foo.crud.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.MultipartConfigElement;

/**
 * @Author allen.luan@simon.com.cn
 * @Date 2021/2/7
 * @Description： 阿里云存储桶配置数据
 **/

@Data
@Component
@ConfigurationProperties(prefix = "oss")
public class OSSconfig {

    /**
     * 域名
     */
    private String endpoint;

    /**
     * api访问控制信息id
     */
    private String accessKeyId;

    /**
     * api访问控制密钥
     */
    private String accessKeySecret;

    /**
     * 桶名
     */
    private String bucketName;

    /**
     * url基础地址
     */
    private String baseUrl;

}
