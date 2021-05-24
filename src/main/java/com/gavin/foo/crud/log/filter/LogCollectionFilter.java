package com.gavin.foo.crud.log.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import com.gavin.foo.crud.log.LogFieldRegistry;
import com.gavin.foo.crud.log.LogstashLoggerImpl;
import com.gavin.foo.crud.log.util.LogstashRecordUtils;
import com.gavin.foo.crud.log.util.PatternUtils;
import org.slf4j.MDC;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import java.util.Map;

/**
 * 日志采集过滤器
 * 用于自定义格式采集日志上报到 Logstash-> ELK
 */
public class LogCollectionFilter extends TurboFilter {

    private static final String FQCN = Logger.class.getName();

    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable throwable) {

        // 兼容原有loggerstashImpl的实现方式, 原有日志不走这个逻辑
        if (marker != null && marker.getName().equals(LogstashLoggerImpl.FQCN)) {
            return FilterReply.NEUTRAL;
        }

        // 符合日志记录的名称进行 logger重置，植入MDC数据
        if (LogFieldRegistry.isExistsLogName(logger.getName())) {
            MDC.put("module", logger.getName());
            LogstashRecordUtils.putBaseLogToMDC();
            FormattingTuple formattingTuple = MessageFormatter.arrayFormat(format, params);

            Map<String, Object> injectMap = PatternUtils.grapKeyValue(formattingTuple.getMessage());
            doInject(injectMap);

            resetLogger(marker, logger, level, format, throwable, params);
            doClean(injectMap);
            return FilterReply.DENY;
        }
        return FilterReply.NEUTRAL;
    }

    /**
     * 重置logger， 使其记录报告的位置正常
     * @param marker
     * @param logger
     * @param level
     * @param format
     * @param throwable
     * @param params
     */
    private void resetLogger(Marker marker, Logger logger, Level level, String format, Throwable throwable, Object[] params) {
        StackTraceElement[] stackTraceElements = CallerData.extract(new Throwable(), FQCN,  logger.getLoggerContext().getMaxCallerDataDepth(), logger.getLoggerContext().getFrameworkPackages());
        LoggingEvent le = new LoggingEvent(FQCN, logger, level, format, throwable, params);
        le.setCallerData(stackTraceElements);
        le.setMarker(marker);
        logger.callAppenders(le);
    }


    /**
     * 日志系统属性注入
     */
    private void doInject(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            MDC.put(entry.getKey(), entry.getValue().toString());
        }
    }

    /**
     * 日志MDC 移除
     */
    private void doClean(Map<String, Object> map) {
        for (String k : map.keySet()) {
            MDC.remove(k);
        }
    }

}