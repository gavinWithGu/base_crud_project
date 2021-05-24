package com.gavin.foo.crud.exception;

import lombok.Getter;

/**
 * Created by rongmc on 2017/7/15.
 */
@Getter
public enum LeaseExceEnums implements LeaseErrorCode {

    //1XXXXXX user message
    USER_DONT_EXISTS("1000000", "用户不存在"),
    USERNAME_EXISTS("1000001", "用户名已存在"),
    
    //2XXXXXX other message......
    
    //未知异常状态
    UNKNOWN_EXCEPTION("500", "未知异常"),
    ;

    private String code;
    private String message;

    LeaseExceEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static LeaseExceEnums getByCode(String code) {
        for (LeaseExceEnums leaseExceEnums : LeaseExceEnums.values()) {
            if (leaseExceEnums.getCode().equals(code)) {
                return leaseExceEnums;
            }
        }
        return UNKNOWN_EXCEPTION;
    }

}
