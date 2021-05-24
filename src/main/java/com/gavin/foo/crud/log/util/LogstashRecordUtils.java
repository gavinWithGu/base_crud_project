package com.gavin.foo.crud.log.util;

import com.gavin.foo.crud.log.BaseLogField;
import com.gavin.foo.crud.log.LogstashArgument;
import com.gavin.foo.crud.log.context.ApplicationInfo;
import com.gavin.foo.crud.log.context.CloudContextHolder;
import org.slf4j.MDC;

import java.lang.reflect.Field;
import java.util.stream.Stream;

/**
 * @author Qiuhua Lai
 * @email qhlai@gizwits.com
 */
public final class LogstashRecordUtils {

    private LogstashRecordUtils() {
    }

    /**
     * 注入 Mapped Diagnostic Context
     *
     * @param obj
     */
    public synchronized static void putMDC(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        Stream.of(fields).forEach(f -> {
            try {
                f.setAccessible(true);
                Object val = f.get(obj);
                if (val != null) {
                    MDC.put(f.getName(), val.toString());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 移除 Mapped Diagnostic Context
     *
     * @param clazz
     */
    public synchronized static void removeMDC(Class<? extends LogstashArgument> clazz) {
        if (clazz.isEnum()) {
            LogstashArgument[] arguments = clazz.getEnumConstants();
            for (LogstashArgument argument: arguments) {
                MDC.remove(argument.getCode());
            }
        } else {
            Stream.of(clazz.getDeclaredFields()).forEach(f -> MDC.remove(f.getName()));
        }
    }


    public synchronized static void putBaseLogToMDC() {
        ApplicationInfo applicationInfo = CloudContextHolder.getApplicationInfo();
        MDC.put(BaseLogField.FIELD_PID.getCode(), applicationInfo.getPid());
        MDC.put(BaseLogField.FIELD_SOURCE.getCode(), applicationInfo.getAppname());
        MDC.put(BaseLogField.FIELD_APPLICATION.getCode(), applicationInfo.getAppname());
        MDC.put(BaseLogField.FIELD_NODE.getCode(), applicationInfo.getIpAddress());
        MDC.put(BaseLogField.FIELD_HOSTNAME.getCode(), applicationInfo.getHostname());
    }


}
