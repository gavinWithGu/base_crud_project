package com.gavin.foo.crud.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Utils - web工具类
 *
 * @author lilh
 * @date 2017 /7/6 13:14
 */
public final class WebUtils {

    private WebUtils() {
    }

    /**
     * Gets header.
     *
     * @param key the key
     * @return the header
     */
    public static String getHeader(String key) {
        return getHttpServletRequest().getHeader(key);
    }

    // mysql的模糊查询时特殊字符转义
 	public static String escapeChar(String before) {
 		if (StringUtils.isNotBlank(before)) {
 			before = before.replaceAll("\\\\", "\\\\\\\\");
 			before = before.replaceAll("_", "\\\\_");
 			before = before.replaceAll("%", "\\\\%");
 		}
 		return before;
 	}
    

    /**
     * Gets http servlet request.
     *
     * @return the http servlet request
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
