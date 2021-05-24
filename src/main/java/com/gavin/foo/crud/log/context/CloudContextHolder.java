package com.gavin.foo.crud.log.context;


import com.gavin.foo.crud.log.util.InetUtils;
import org.springframework.context.ApplicationContext;

/**
 * 用于获取注册应用相关信息
 *
 * @author Qiuhua Lai
 * @email qhlai@gizwits.com
 */
public final class CloudContextHolder {

    private static ApplicationInfo applicationInfo = new ApplicationInfo();

    static {
        InetUtils.HostInfo hostInfo = new InetUtils().findFirstNonLoopbackHostInfo();
        applicationInfo.setHostname(hostInfo.getHostname());
        applicationInfo.setIpAddress(hostInfo.getIpAddress());
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        if (applicationContext != null) {
            applicationInfo.setPid(System.getProperty("PID"));
            applicationInfo.setId(applicationContext.getId());
            applicationInfo.setAppname(applicationContext.getId().split(":")[0]);
        }

    }


    /**
     * 获取应用基本信息
     * @return
     */
    public static ApplicationInfo getApplicationInfo() {
        return applicationInfo;
    }


}
