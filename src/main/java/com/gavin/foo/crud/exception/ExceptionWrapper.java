package com.gavin.foo.crud.exception;


import com.gavin.foo.crud.base.web.ResponseObject;

/**
 *
 * @Author Gavin
 * @Description 封装系统和异常类
 * @Date 2021/4/8 11:13
 **/
public class ExceptionWrapper {

    /**
     * 记录下层服务的异常状态  message_code, msg
     */
    public static final String INNER_ERROR_MSG = "下层服务系统异常:[msg_code={},msg={}]";

    public static void throwSystemException(LeaseErrorCode leaseExceEnums) {
        throw new SystemException(leaseExceEnums.getCode(), leaseExceEnums.getMessage());
    }

    public static void throwSystemException(LeaseErrorCode leaseExceEnums, String appendMessage) {
        throw new SystemException(leaseExceEnums.getCode(), leaseExceEnums.getMessage() + appendMessage);
    }

    public static void throwSystemException(String frontMessage, LeaseErrorCode leaseExceEnums) {
        throw new SystemException(leaseExceEnums.getCode(), frontMessage + leaseExceEnums.getMessage());
    }

    public static void throwSystemException(String frontMessage, LeaseErrorCode leaseExceEnums, String appendMessage) {
        throw new SystemException(leaseExceEnums.getCode(), frontMessage + leaseExceEnums.getMessage() + appendMessage);
    }

    public static void throwSystemException(LeaseExceEnums exceEnums) {
        throw new SystemException(exceEnums.getCode(), exceEnums.getMessage());

    }

    public static void throwSystemException(String code, String msg) {
        throw new SystemException(code, msg);

    }

    public static void throwSystemException(String frontMessage, SystemExceptionEnum exceEnums) {
        throw new SystemException(exceEnums.getCode(), frontMessage + exceEnums.getMessage());
    }


    public static void throwSystemException(SystemExceptionEnum exceEnums) {
        throw new SystemException(exceEnums.getCode(), exceEnums.getMessage());
    }

    /**
     * 抛出服务层调用的异常信息
     *
     * @param code 错误码
     * @param msg  错误消息
     */
    public static void throwServiceException(String code, String msg) {
        throw new SystemException(code, msg);
    }

    /**
     * 抛出服务层调用的异常信息
     *
     * @param object 返回的响应体
     */
    public static void throwServiceException(ResponseObject object) {
        throwServiceException(object.getCode(), object.getMessage());
    }




}

