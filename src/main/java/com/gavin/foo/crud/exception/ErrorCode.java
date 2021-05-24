package com.gavin.foo.crud.exception;

/**
 * 错误码基类，所有错误码都需要实现此接口
 */
public interface ErrorCode {
    /**
     * 错误码
     *
     * @return
     */
    String getCode();

    /**
     * 错误信息
     *
     * @return
     */
    String getMessage();

}
