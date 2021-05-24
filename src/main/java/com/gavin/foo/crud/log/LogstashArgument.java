package com.gavin.foo.crud.log;

/**
 * 日志记录，传输到logstash的字段，需要符合AEP的标准
 *
 * @author Qiuhua Lai
 * @email qhlai@gizwits.com
 */
public interface LogstashArgument {

    String getCode();
}
