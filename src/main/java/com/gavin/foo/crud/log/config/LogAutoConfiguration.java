package com.gavin.foo.crud.log.config;

import ch.qos.logback.classic.Logger;
import com.gavin.foo.crud.log.context.CloudContextHolder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 只有用了logback 才会启用
 * @Author qhlai
 * @email qhlai@gizwits.com
 */
@ConditionalOnClass(Logger.class)
@Configuration
public class LogAutoConfiguration {

    @Bean
    public CloudContextHolder cloudContextHolder(ApplicationContext applicationContext) {
        CloudContextHolder.setApplicationContext(applicationContext);
        return new CloudContextHolder();
    }

}
