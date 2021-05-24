package com.gavin.foo.crud.exception;

import lombok.Data;
import lombok.ToString;

/**
 * Created by rongmc on 16/5/31.
 */
@Data
@ToString
public class SystemException extends RuntimeException {

    private String code;

    private String message;

    public SystemException(SystemExceptionEnum sysExceptionEnum) {
        this(sysExceptionEnum.getCode(), sysExceptionEnum.getMessage());
    }

    public SystemException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
