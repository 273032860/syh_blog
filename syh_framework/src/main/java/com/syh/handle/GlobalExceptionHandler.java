package com.syh.handle;

import com.syh.domain.ResponseResult;
import com.syh.enums.AppHttpCodeEnum;
import com.syh.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//抛出异常统一封装处理
//抛出异常的使用： throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME)
//然后通过AppHttpCodeEnum 枚举类封装的errorResult(e.getCode(),e.getMsg()) 和SystemException类  返回错误提示信息
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e){
        //打印异常信息
        log.error("出现了异常！ {}",e);
        //从异常对象中获取提示信息封装返回
        return ResponseResult.errorResult(e.getCode(),e.getMsg());
    }


    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e){
        //打印异常信息
        log.error("出现了异常！ {}",e);
        //从异常对象中获取提示信息封装返回
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),e.getMessage());
    }
}
