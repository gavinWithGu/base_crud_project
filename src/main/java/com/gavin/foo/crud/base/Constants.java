package com.gavin.foo.crud.base;

/**
 * Created by rongmc on 2017/7/2.
 */
public class Constants {

    //数据库基本的搜索列
    public static final String ID = "id";

    public static final String CTIME = "ctime";

    public static final String UTIME = "utime";
    /**
     *
     */
    public static final String ACCESS_TOKEN_CACHE_NAME = "access_token";
    /**
     * TOKEN在http header 中的name
     */
    public static final String TOKEN_HEADER_NAME = "Authorization";
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 邮箱正则
     */
    public final static String MAIL_REGEX = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    public static long refreshTime = 0l;
    /**
     * 全球手机号码正则
     */
    public static String GLOBAL_PHONE_REG = "^(\\+|00){0,2}(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|4[987654310]|3[9643210]|2[70]|7|1)?\\d{1,14}$";
    /**
     * 中国手机号码正则
     */
    public static String CHINA_PHONE_REGEX = "((\\+86)|(86))?1\\d{10}";

}
