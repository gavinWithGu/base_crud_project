package com.gavin.foo.crud.log;

/**
 *
 * 需要通过logstash记录到elk的logger注册器, 只能通过枚举实现
 *
 * @author Qiuhua Lai
 * @email qhlai@gizwits.com
 */
public interface LogstashLogName<T extends Enum> {

     /**
      * 获取所有的logger 日志打印器
      * @return
      */
     T[] getEnums();

     /**
      * logger module 模块名称，用于模块化记录日志
      * @return
      */
     String getModuleName();

     /**
      * logger module 模块名称，用于模块化记录日志
      * @return
      */
     String getLoggerName();

     /**
      * logger 描述
      * @return
      */
     String getDesc();


}
