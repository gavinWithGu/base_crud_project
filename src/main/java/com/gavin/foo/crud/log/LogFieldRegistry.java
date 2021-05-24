package com.gavin.foo.crud.log;

import com.gavin.foo.crud.log.module.BusinessModule;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 日志业务字段注册器， 用于用户自定义字段枚举注册
 * @Author qhlai
 * @email qhlai@gizwits.com
 */
public class LogFieldRegistry {
    private static Set<LogstashLogName> LogRegistries = new HashSet<>();
    private static Set<LogstashArgument> ArgumentRegistries = new HashSet<>();

    private LogFieldRegistry(){}

    /**
     * 默认系统日志字段
     */
    static {
//        LogRegistries.addAll(Arrays.asList(BusinessCode.values()));
        LogRegistries.addAll(Arrays.asList(BusinessModule.values()));
        ArgumentRegistries.addAll(Arrays.asList(BaseLogField.values()));
        ArgumentRegistries.addAll(Arrays.asList(BusinessLogFieldEnum.values()));
    }

    /**
     * 注册业务日志字段， 必须是Enum 和 实现LogstashArgument接口
     * @param clazz
     */
    public static void registryLogName(Class<? extends LogstashLogName> clazz) {
        if (!clazz.isEnum()) {
            throw new IllegalArgumentException("it must be an enum");
        }
        LogRegistries.addAll(Arrays.asList(clazz.getEnumConstants()));
    }

    /**
     * 注册业务日志字段， 必须是Enum 和 实现LogstashArgument接口
     * @param clazz
     */
    public static void registryLogField(Class<? extends LogstashArgument> clazz) {
        if (!clazz.isEnum()) {
            throw new IllegalArgumentException("it must be an enum");
        }
        ArgumentRegistries.addAll(Arrays.asList(clazz.getEnumConstants()));
    }

    /**
     * 获取已经注册的logger name
     * @return
     */
    public static Set<LogstashLogName> getLogNameRegistries() {
        return LogRegistries;
    }

    /**
     * 获取已经注册的业务字段
     * @return
     */
    public static Set<LogstashArgument> getLogFieldRegistries() {
        return ArgumentRegistries;
    }

    /**
     * 判断记录logger name是否存在
     * @param loggerName
     * @return
     */
    public static boolean isExistsLogName(String loggerName) {
        return LogRegistries.stream().anyMatch(x->x.getLoggerName().equalsIgnoreCase(loggerName));
    }

    /**
     * 判断记录日志字段是否存在
     * @param logfField
     * @return
     */
    public static boolean isExistsLogField(String logfField) {
        return ArgumentRegistries.stream().anyMatch(x->x.getCode().equalsIgnoreCase(logfField));
    }


}
