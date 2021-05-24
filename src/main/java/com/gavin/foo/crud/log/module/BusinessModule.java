package com.gavin.foo.crud.log.module;

import com.gavin.foo.crud.log.LogstashLogName;

import java.util.stream.Stream;

/**
 * 业务模块码
 *
 * @author Qiuhua Lai
 * @email qhlai@gizwits.com
 */
public enum BusinessModule implements LogstashLogName<BusinessModule> {

    SYS_MODULE("SYS_LOGGER", "SYS_LOG_APPENDER", "系统日志模块"),
    ES_MODULE("ES_LOGGER", "ES_APPENDER", "ES日志模块"),
    SMART_HOME_MODULE("SMART_HOME_LOGGER", "SMART_HOME_LOG_APPENDER", "智家日志模块"),
    ;

    BusinessModule(String loggerName, String appenderName, String desc) {
        this.loggerName = loggerName;
        this.appenderName = appenderName;
        this.desc = desc;
    }

    private String loggerName;
    private String appenderName;
    private String desc;

    @Override
    public BusinessModule[] getEnums() {
        return values();
    }

    @Override
    public String getModuleName() {
        return name();
    }

    @Override
    public String getLoggerName() {
        return loggerName;
    }

    public String getAppenderName() {
        return appenderName;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return getLoggerName();
    }

    public static boolean isExists(String loggerName) {
        return Stream.of(BusinessModule.values()).anyMatch(b -> b.getLoggerName().equals(loggerName));
    }

    public static BusinessModule getLogger(String loggerName) {
        return Stream.of(BusinessModule.values()).filter(f -> f.getLoggerName().equals(loggerName)).findFirst().orElse(null);
    }

}
