package com.gavin.foo.crud.exception;

import com.gavin.foo.crud.base.web.ResponseObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author allen.luan@simon.com.cn
 * @Date 2021/2/7
 * @Description：全局统一异常处理
 **/

@Slf4j
@ControllerAdvice
public class ExceptionHandler {

    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public ResponseObject globalExceptionhandler(Exception e){
        log.error("系统服务异常. error Message [{}], error Cause [{}]",e.getMessage(), e.getCause());
        return ResponseObject.response("500", e.getMessage());
    }

    /**
     * 设备异常处理
     * @param e
     * @return Result
     */
    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(value = SystemException.class)
    public ResponseObject SystemException(SystemException e){
        log.error("系统服务异常. error code [{}], error message [{}]",e.getCode(), e.getMessage());
        return ResponseObject.response(e.getCode(), e.getMessage());
    }
}
