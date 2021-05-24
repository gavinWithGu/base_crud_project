package com.gavin.foo.crud.log;

import com.gavin.foo.crud.log.util.LogstashRecordUtils;
import com.gavin.foo.crud.log.util.PatternUtils;
import net.logstash.logback.argument.StructuredArgument;
import org.slf4j.*;
import org.slf4j.event.Level;
import org.slf4j.helpers.FormattingTuple;
import org.springframework.data.util.Pair;

/**
 * 自定义日志实现， 结合logstash, 标准化日志输出
 *
 * @author Qiuhua Lai
 * @email qhlai @gizwits.com
 */
public class LogstashLoggerImpl extends AbstractSlf4jLogger implements LogstashLogger {
    private final Logger logger;
    public static final String FQCN = LogstashLoggerImpl.class.getName();
    /**
     * Marker to be used by logging implementations that support markers
     */
    private static final Marker MARKER = MarkerFactory.getMarker(FQCN);

    /**
     * 默认模块为root
     */
    private String module = Logger.ROOT_LOGGER_NAME;
    /**
     * Instantiates a new Logstash logger.
     *
     * @param clazz the clazz
     */
    public LogstashLoggerImpl(Class<?> clazz) {
        this(clazz.getName());
    }

    /**
     * Instantiates a new Logstash logger.
     *
     * @param name the logger
     */
    public LogstashLoggerImpl(String name) {
        this.logger = LoggerFactory.getLogger(name);
        this.name = logger.getName();
    }

    public Logger getLogger() {
        return logger;
    }

    public String getModule() {
        return module;
    }

    public LogstashLogger setModule(String module) {
        this.module = module;
        return this;
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled(MARKER);
    }

    @Override
    public void trace(String format, Object... arguments) {
        log(Level.TRACE, format, arguments);
    }

    public void trace(String format, StructuredArgument... arguments) {
        log(Level.TRACE, format, arguments);
    }

    @Override
    public void trace(String msg, Throwable t) {
        log(Level.TRACE, msg, t);
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled(MARKER);
    }

    @Override
    public void debug(String format, Object... arguments) {
        log(Level.DEBUG, format, arguments);
    }

    public void debug(String format, StructuredArgument... arguments) {
        log(Level.DEBUG, format, arguments);
    }

    @Override
    public void debug(String msg, Throwable t) {
        log(Level.DEBUG, msg, t);
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled(MARKER);
    }

    @Override
    public void info(String msg) {
        log(Level.INFO, msg);
    }

    @Override
    public void info(String format, Object... arguments) {
        log(Level.INFO, format, arguments);
    }

    public void info(String format, StructuredArgument... arguments) {
        log(Level.INFO, format, arguments);
    }

    @Override
    public void info(String msg, Throwable t) {
        log(Level.INFO, msg, t);
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled(MARKER);
    }

    @Override
    public void warn(String msg) {
        log(Level.WARN, msg);
    }

    @Override
    public void warn(String format, Object... arguments) {
        log(Level.WARN, format, arguments);
    }

    public void warn(String format, StructuredArgument... arguments) {
        log(Level.WARN, format, arguments);
    }

    @Override
    public void warn(String msg, Throwable t) {
        log(Level.WARN, msg, t);
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled(MARKER);
    }

    @Override
    public void error(String format, Object... arguments) {
        log(Level.ERROR, format, arguments);
    }

    public void error(String format, StructuredArgument... arguments) {
        log(Level.ERROR, format, arguments);
    }

    @Override
    public void error(String msg, Throwable t) {
        log(Level.ERROR, msg, t);
    }

    private void log(Level level, String message, Object... arguments) {
        Pair<FormattingTuple, StructuredArgument> pair = PatternUtils.format(message, arguments);
        String formatMsg = pair.getFirst().getMessage();
        Throwable throwable = pair.getFirst().getThrowable();
        doClean();
        doInject();
        ch.qos.logback.classic.Logger clogger = (ch.qos.logback.classic.Logger) logger;
        clogger.log(MARKER, FQCN, level.toInt(), formatMsg, new Object[] {pair.getSecond()}, throwable);
    }

    /**
     * 日志系统属性注入
     */
    private void doInject() {
        MDC.put(BaseLogField.FIELD_MODULE.getCode(), this.getModule());
        LogstashRecordUtils.putBaseLogToMDC();
    }

    /**
     * 日志MDC 移除
     */
    private void doClean() {
        LogstashRecordUtils.removeMDC(BusinessLogFieldEnum.class);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof LogstashLoggerImpl))
            return false;

        final LogstashLoggerImpl other = (LogstashLoggerImpl) obj;
        return name.equals(other.getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }


}
