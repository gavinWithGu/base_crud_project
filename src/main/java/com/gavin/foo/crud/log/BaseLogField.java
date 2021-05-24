package com.gavin.foo.crud.log;

import java.util.stream.Stream;

/**
 * @author Qiuhua Lai
 * @email qhlai@gizwits.com
 */
public enum BaseLogField implements LogstashArgument {

    /**
     * 进程id
     */
    FIELD_PID("pid"),
    /**
     * 来源
     */
    FIELD_SOURCE("source"),
    /**
     * 节点
     */
    FIELD_NODE("node"),
    /**
     * 应用
     */
    FIELD_APPLICATION("application"),

    /**
     * 服务运行的hostname
     */
    FIELD_HOSTNAME("hostname"),

    /**
     * 模块名称
     */
    FIELD_MODULE("module"),

    /**
     * 客户请求id
     */
    FIELD_REQUEST_ID("request_id"),

    /**
     * 服务调用链追踪id
     */
    FIELD_TRACE_ID("trace_id"),


    /**
     * 服务调用链分段id
     */
    FIELD_SPAN_ID("span_id"),
    /**
     * 用户浏览器信息
     */
    FIELD_USER_AGENT("user_agent"),

    /**
     * 服务版本
     */
    FIELD_VERSION("version"),

    ;

    BaseLogField(String code) {
        this.code = code;
    }

    private String code;

    @Override
    public String getCode() {
        return code;
    }


    public static boolean isExists(String field) {
        return Stream.of(BaseLogField.values()).anyMatch(b -> b.code.equals(field));
    }

    @Override
    public String toString() {
        return code;
    }
}
