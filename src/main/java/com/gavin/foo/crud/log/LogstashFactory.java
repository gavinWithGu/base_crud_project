package com.gavin.foo.crud.log;

import com.gavin.foo.crud.log.util.LoggerUtils;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 获取日志,通过缓存提高运行效率
 *
 * @author Qiuhua Lai
 * @email qhlai @gizwits.com
 */
public final class LogstashFactory {
    /**
     * Marker to be used by logging implementations that support markers
     */
    private static Map<String, LogstashLogger> loggers = new ConcurrentHashMap<>();
    private static Constructor<? extends LogstashLogger> logConstructor;

    static {
        setImplementation(LogstashLoggerImpl.class);
    }

    private LogstashFactory() {
    }

    /**
     * Gets logger.
     *
     * @param name the name
     * @return the logger
     */
    public static LogstashLogger getLogger(String name) {
        return getCacheLogger(name);
    }

    /**
     * Gets logger.
     *
     * @param logstashLogName the business code
     * @return the logger
     */
    public static LogstashLogger getLogger(LogstashLogName logstashLogName) {
        return getCacheLogger(logstashLogName.getLoggerName()).setModule(logstashLogName.getModuleName());
    }

    /**
     * Gets logger.gi
     *
     * @param clazz the clazz
     * @return the logger
     */
    public static LogstashLogger getLogger(Class<?> clazz) {
        return getCacheLogger(clazz.getName());
    }

    /**
     * 重新初始化日志 文件输出格式， 仅仅支持已经在logback.xml定义的logger和appenderName
     *
     * @param loggerName   the logger name
     * @param AppenderName the appender name
     * @param pattern      日志输出格式
     */
    public static void initAppenderPattern(String loggerName, String AppenderName, String pattern) {
        LoggerUtils.setAndgetPattern(new LogstashLoggerImpl(loggerName).getLogger(), AppenderName, pattern);
    }

    /**
     * cache logger
     * @param name
     * @return
     */
    private static LogstashLogger getCacheLogger(String name) {
        LogstashLogger instance = loggers.get(name);
        if (instance != null) {
            return instance;
        } else {
            try {
                LogstashLogger newInstance = logConstructor.newInstance(name);
                loggers.putIfAbsent(name, newInstance);
                return newInstance;
            } catch (Exception t) {
                throw new ExceptionInInitializerError("Error creating logger for logger " + name + ".  Cause: " + t);
            }
        }
    }


    /**
     * 使用Slf4j实现.
     */
    public static synchronized void useSlf4jLogging() {
        setImplementation(LogstashLoggerImpl.class);
    }

    private static void tryImplementation(Runnable runnable) {
        if (logConstructor == null) {
            try {
                runnable.run();
            } catch (Exception t) {
                System.err.println(t);
            }
        }
    }

    private static void setImplementation(Class<? extends LogstashLogger> implClass) {
        try {
            Constructor<? extends LogstashLogger> candidate = implClass.getConstructor(String.class);
            LogstashLogger log = candidate.newInstance(LogstashFactory.class.getName());
            if (log.isDebugEnabled()) {
                log.debug("Logging initialized using '" + implClass + "' adapter.");
            }
            logConstructor = candidate;
        } catch (Throwable t) {
            throw new ExceptionInInitializerError("Error setting Log implementation.  Cause: " + t);
        }
    }

}
