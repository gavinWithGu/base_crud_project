package com.gavin.foo.crud.log.constant;

/**
 * @author Qiuhua Lai
 * @email qhlai@gizwits.com
 */
public class LogPatternConstant {
    public static final String DEFAULT_PATTERN = "%d{yyyy-MM-dd HH:mm:ss.SSS} %highlight(%-5p) %yellow(${PID:- }) --- [%-10.10t] %cyan(%-40.40logger{39}) %M %L : %m%n";
    public static final String DEFAULT_FILE_PATTERN = "%d{yyyy-MM-dd HH:mm:ss.SSS} %-5p ${PID:- } --- [%t] %-40.40logger{39} %M %L : %m%n";
    public static final String LOGSTASH_PATTERN = "%d{yyyy-MM-dd HH:mm:ss.SSS} %-5p %X{pid:-}} --- [%10.10t] %C{50} %M %L [syslog]:[{pid:%X{PID:-}},{source: %X{source:-}},{node: %X{node:-}},{application:%X{application:-}},{module:%X{module:-}},{apiVersion:%X{apiVersion:-}},{traceId:%X{traceId:-}},{spanId:%X{spanId:-}},{httpStatus:%X{httpStatus:-}}] [businesslog]:[{ip:%X{ip:-}},{mac:%X{mac:-}},{did:%X{did:-}},{product_key:%X{product_key:-}},{device_sn:%X{device_sn:-}},{enterprise_id:%X{enterprise_id:-}},{user_name:%X{username:-}},{user_id:%X{user_id:-}},{gizwits_uid:%X{gizwits_uid:-}},{openid:%X{openid:-}},{third_order_no:%X{third_order_no:-}},{order_no:%X{order_no:-}},{msg:%X{msg:-}},{msg_code:%X{msg_code:-}},{extend:%X{extend:-}}] : %m%n";
    public static final String LOGSTASH_JSON_PATTERN = "{\"created_at\":\"%d{yyyy-MM-dd HH:mm:ss.SSS}\",\"level\":\"%-5p\",\"pid\":\"%X{pid:-}\",\"threat\":\"%t\",\"moduleName\":\"%C{50}\",\"method\":\"%M\",\"line\":\"%L\",\"source\":\"%X{source:-}\",\"node\": \"%X{node:-}\",\"application\":\"%X{application:-}\",\"module\":\"%X{module:-}\",\"apiVersion\":\"%X{apiVersion:-}\",\"traceId\":\"%X{traceId:-}\",\"spanId\":\"%X{spanId:-}\",\"httpStatus\":\"%X{httpStatus:-}\",\"ip\":\"%X{ip:-}\",\"mac\":\"%X{mac:-}\",\"did\":\"%X{did:-}\",\"product_key\":\"%X{product_key:-}\",\"device_sn\":\"%X{device_sn:-}\",\"enterprise_id\":\"%X{enterprise_id:-}\",\"user_name\":\"%X{username:-}\",\"user_id\":\"%X{user_id:-}\",\"gizwits_uid\":\"%X{gizwits_uid:-}\",\"openid\":\"%X{openid:-}\",\"third_order_no\":\"%X{third_order_no:-}\",\"order_no\":\"%X{order_no:-}\",\"msg\":\"%X{msg:-}\",\"msg_code\":\"%X{msg_code:-}\",\"extend\":\"%X{extend:-}\",\"message\": \"%m\"}%n";
}
