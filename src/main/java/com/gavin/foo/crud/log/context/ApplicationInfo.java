package com.gavin.foo.crud.log.context;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Qiuhua Lai
 * @email qhlai@gizwits.com
 */
@ToString
@Setter
@Getter
public class ApplicationInfo {

    private String id;


    private String pid;

    /**
     * 应用名称，serviceId
     */
    private String appname;

    /**
     * eureka 注册中心hostname
     */
    private String hostname;
    /**
     * eureka 注册中心ipAddress
     */
    private String ipAddress;

    /**
     * 实例id
     */
    private String instanceId;

    /**
     *  UP, // Ready to receive traffic
     *  DOWN, // Do not send traffic- healthcheck callback failed
     *  STARTING, // Just about starting- initializations to be done - do not send traffic
     *  OUT_OF_SERVICE, // Intentionally shutdown for traffic
     *  UNKNOWN;
     */
    private String initialStatus;

}
