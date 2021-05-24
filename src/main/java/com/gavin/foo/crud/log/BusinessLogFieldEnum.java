package com.gavin.foo.crud.log;

import java.util.stream.Stream;

/**
 * @author Qiuhua Lai
 * @email qhlai@gizwits.com
 */
public enum BusinessLogFieldEnum implements LogstashArgument {

    /**
     * 客户端ip
     */
    FIELD_IP("ip"),
    /**
     * 设备mac地址
     */
    FIELD_MAC("mac"),
    /**
     * imei
     */
    FIELD_IMEI("imei"),
    /**
     * 设备did
     */
    FIELD_DID("did"),
    /**
     * 产品productKey
     */
    FIELD_PRODUCT_KEY("product_key"),
    /**
     * 设备sn
     */
    FIELD_DEVICE_SN("device_sn"),
    /**
     * 企业id
     */
    FIELD_ENTERPRISE_ID("enterprise_id"),
    /**
     * 用户名称
     */
    FIELD_USER_NAME("user_name"),
    /**
     * 用户id
     */
    FIELD_USER_ID("user_id"),

    /**
     * 机智云uid
     */
    FIELD_GIZWITS_UID("gizwits_uid"),
    /**
     * openid
     */
    FIELD_OPENID("openid"),
    /**
     * 第三方订单号
     */
    FIELD_THIRD_ORDER_NO("3rd_order_no"),
    /**
     * Saas订单号
     */
    FIELD_ORDER_NO("order_no"),
    /**
     * 业务信息id
     */
    FIELD_MSG_ID("msg_id"),

    /**
     * 业务码
     */
    FIELD_MSG_CODE("msg_code"),

    /**
     * 消息类型
     */
    FIELD_MSG_TYPE("msg_type"),

    /**
     * 业务信息返回
     */
    FIELD_MSG("msg"),

    /**
     * http 请求返回状态码
     */
    FIELD_HTTP_STATUS("http_status"),

    /**
     * http请求url
     */
    FIELD_HTTP_URL("http_url"),

    /**
     * http请求方法
     */
    FIELD_HTTP_METHOD("http_method"),

    /**
     * 接收请求时间
     */
    FIELD_RECEIVED_TS("received_ts"),

    /**
     * 前端操作系统
     */
    FIELD_OS("os"),

    /**
     * 业务名称
     */
    FIELD_BUSI_NAME("busi_name"),

    /**
     * 消息码
     */
    /**
     * 拓展使用<str 255> 可能会使用到
     */
    FIELD_EXTEND("extend"),

    /**
     * 花费时间
     */
    COST_TIME("cost_time"),

    /**
     * 应用appid
     */
    APPID("appid"),

    /**
     * 域domainid
     */
    DOMAIN_ID("domain_id"),

    /**
     * 请求api version版本
     */
    API_VERSION("api_version"),

    /**
     * HTTP请求方法
     */
    HTTP_METHOD("http_method"),

    /**
     * 请求Controller method
     */
    REQUEST_METHOD("request_method"),

    /**
     * 请求url
     */
    URI("uri"),

    /**
     * 手机
     */
    MOBILE("mobile"),

    /**
     * 邮箱
     */
    EMAIL("email"),

    /**
     * token, 不建议写入到日志
     */
    TOKEN("token"),

    /**
     * 过期时间
     */
    EXPIRE_AT("expire_at"),

    /**
     * 国家
     */
    COUNTRY("country"),

    /**
     * 城市
     */
    CITY("city"),

    /**
     * 地区
     */
    REGION("region"),

    /**
     * 事件类型
     */
    EVENT_TYPE("event_type"),

    /**
     * 服务分层编码 <br/>
     * 代码	分层名称 <br/>
     * 0	保留 <br/>
     * 1	保留 <br/>
     * 2	核心数据层（DAO） <br/>
     * 3	基础连接层 <br/>
     * 4	基础核心层 <br/>
     * 5	基础逻辑层 <br/>
     * 6	基础业务层 <br/>
     * A	扩展业务层 <br/>
     * 服务编码 <br/>
     * 服务层级	服务名称	项目名称	服务日志编码 <br/>
     * 基础业务层	用户中心		6001 <br/>
     * 基础连接层	MQTT Broker		3001 <br/>
     * 业务代码
     * <p>
     * 用户中心业务举例 <br/>
     * 业务名称	biz_cod <br/>
     * 创建用户	60010010 <br/>
     * 第三方用户授权	60010015 <br/>
     * 用户登录	60010020 <br/>
     * 生成用户验证短码	60010030 <br/>
     * 校验用户验证短码	60010031 <br/>
     * 获取用户信息	60010040 <br/>
     * 更新用户信息	60010011 <br/>
     * 生成手机验证码	60010050 <br/>
     * 校验手机验证码	60010051 <br/>
     * 生成图片验证码	60010052 <br/>
     * 校验图片验证码	60010053 <br/>
     * 根据 Refresh Token 生成新的用户 Token	60010053 <br/>
     */
    FIELD_BIZ_CODE("biz_code"),
    /**
     * 业务结果
     */
    FIELD_BIZ_RESULT("biz_result"),
    /**
     * 业务错误码
     */
    FIELD_ERR_CODE("err_code"),

    /**
     * 用户登录字段
     * <p>
     * 0 => anonymous: 匿名登录 <br/>
     * 1 => user_pwd: 用户名密码登录 <br/>
     * 2 => phone_pwd: 手机密码登录 <br/>
     * 3 => email_pwd: 邮箱密码登录 <br/>
     * 4 => phone_code: 手机验证码登录 <br/>
     * 5 => email_code: 邮箱验证码登录 <br/>
     * 6 => third_part: 第三方授权登录 <br/>
     * 600 => undefined <br/>
     * 601 => wechat: 微信 <br/>
     * 602 => qq: QQ <br/>
     * 603 => alipay: 支付宝 <br/>
     * 604 => baidu: 百度 <br/>
     * 605 => sina: 新浪微博 <br/>
     * 631 => google: 谷歌 <br/>
     * 632 => twitter: 推特 <br/>
     * 633 => facebook: 脸谱 <br/>
     * 634 => amazon: 亚马逊 <br/>
     */
    FIELD_LOGIN_TYPE("login_type"),


    ;

    BusinessLogFieldEnum(String code) {
        this.code = code;
    }

    private String code;

    @Override
    public String getCode() {
        return code;
    }


    public static boolean isExists(String field) {
        return Stream.of(BusinessLogFieldEnum.values()).anyMatch(b -> b.code.equals(field));
    }

    @Override
    public String toString() {
        return code;
    }
}
