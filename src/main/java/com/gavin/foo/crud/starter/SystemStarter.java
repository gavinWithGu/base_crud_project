package com.gavin.foo.crud.starter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author Gavin
 * @Description 系统启动类：执行初始化任务
 * @Date 2021/4/7 10:36
 **/
@Data
@Slf4j
@Component
public class SystemStarter implements CommandLineRunner {
    private static final byte[] lock = new byte[0]; //內部對象鎖

    /**
     * Springboot初始化完成以后的回调方法
     *
	 * @date: 2021/4/7 10:37
	 * @param args
     * @return:
     */
    @Override
    public void run(String... args) throws Exception {
        log.info("Start system initial task!");
        // TODO：系统初始化的任务
        synchronized (lock) {
            lock.wait(2000l);
        }
        log.info("Finish system initial task!");
    }
}
