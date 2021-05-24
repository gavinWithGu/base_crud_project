package com.gavin.foo.crud.log.util;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import org.slf4j.Logger;

import java.nio.charset.StandardCharsets;

/**
 * 动态修改日志格式，以及添加日志
 * @author Qiuhua Lai
 * @email qhlai@gizwits.com
 */
public final class LoggerUtils {
    private LoggerUtils(){}

    /**
     * 更新fileAppender的pattern, 之后采用新的pattern输出
     * @param logger
     * @param appenderName 文件appenderName
     * @param pattern 日志输出格式
     * @return
     */
    public static Logger setAndgetPattern(Logger logger, String appenderName, String pattern) {
        if (logger == null) return null;
        ch.qos.logback.classic.Logger logger1 = (ch.qos.logback.classic.Logger) logger;
        RollingFileAppender<ILoggingEvent> fileAppender = (RollingFileAppender<ILoggingEvent>) logger1.getAppender(appenderName);
        if (fileAppender == null) return null;
        PatternLayoutEncoder patternLayoutEncoder = (PatternLayoutEncoder) fileAppender.getEncoder();
        patternLayoutEncoder.setPattern(pattern);
        patternLayoutEncoder.start();
        fileAppender.start();
        logger1.addAppender(fileAppender);
        return logger;
    }

    /**
     * logger创建fileAppender
     * @param logger
     * @param encoder 文件输出编码
     * @param rollingPolicy 滚动策略
     * @param appenderName 文件追加日志名称
     * @param fileName 储存文件名称
     * @return
     */
    public static Logger createRollingFileAppender(Logger logger, PatternLayoutEncoder encoder, SizeAndTimeBasedRollingPolicy rollingPolicy, String appenderName, String fileName) {
        ch.qos.logback.classic.Logger logger1 = (ch.qos.logback.classic.Logger) logger;
        RollingFileAppender<ILoggingEvent> fileAppender = new RollingFileAppender<>();
        fileAppender.setContext(logger1.getLoggerContext());
        fileAppender.setEncoder(encoder);
        fileAppender.setRollingPolicy(rollingPolicy);
        fileAppender.setName(appenderName);
        fileAppender.setFile(fileName);
        fileAppender.start();
        logger1.addAppender(fileAppender);
        return logger;
    }

    /**
     * 创建日志输出编码
     * @param loggerContext 日志上下文
     * @param patern 日志输出格式
     * @return
     */
    public static PatternLayoutEncoder createPatternLayoutEncoder(LoggerContext loggerContext, String patern) {
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(loggerContext);
        encoder.setPattern(patern);
        encoder.setCharset(StandardCharsets.UTF_8);
        encoder.start();
        return encoder;
    }

    /**
     * 创建文件滚动策略
     * @param filenamePattern 文件名称格式，支持正则
     * @param maxFileSize 文件最大尺寸，可以是KB,MB,GB
     * @param maxHistory 最大历史保存天数
     * @param totalSize 总共文件尺寸，超过这个尺寸会删除，可以是KB,MB,GB
     */
    public static SizeAndTimeBasedRollingPolicy createRollingPolicy(String filenamePattern, String maxFileSize, int maxHistory, String totalSize) {
        SizeAndTimeBasedRollingPolicy rollingPolicyBase = new SizeAndTimeBasedRollingPolicy();
        rollingPolicyBase.setFileNamePattern(filenamePattern);
        rollingPolicyBase.setMaxFileSize(FileSize.valueOf(maxFileSize));
        rollingPolicyBase.setMaxHistory(maxHistory);
        rollingPolicyBase.setTotalSizeCap(FileSize.valueOf(totalSize));
        return rollingPolicyBase;
    }

    /**
     *
     * @param logger
     * @param filenamePattern 文件名称格式，支持正则
     * @param appenderName 文件追加名称
     * @param fileName 文件名称（日志初始化当前使用的文件名）
     * @param pattern 日志输出格式
     */
    public static Logger createDefaultRollingFileAppender(Logger logger, String filenamePattern, String appenderName, String fileName, String pattern) {
        ch.qos.logback.classic.Logger logger1 = (ch.qos.logback.classic.Logger) logger;
        SizeAndTimeBasedRollingPolicy rollingPolicy = createRollingPolicy(filenamePattern, "10MB", 7, "1G");
        PatternLayoutEncoder encoder = createPatternLayoutEncoder(logger1.getLoggerContext(), pattern);
        return createRollingFileAppender(logger, encoder, rollingPolicy, appenderName, fileName);
    }



}
