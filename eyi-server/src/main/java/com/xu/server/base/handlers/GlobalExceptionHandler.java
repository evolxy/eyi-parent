package com.xu.server.base.handlers;

import cn.dev33.satoken.exception.NotRoleException;
import com.xu.commons.exception.EyiException;
import com.xu.commons.result.Result;
import com.xu.commons.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/14 14:12
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(NotRoleException.class)
    public Result<?> handleNotRoleExp(NotRoleException e) {
        log.error(e.getMessage(), e);
        return Result.failed(ResultCode.NOT_PERMISSION);
    }

    @ExceptionHandler(EyiException.class)
    public Result<?> handleEyiExp(EyiException e) {
        log.error(e.getMessage(), e);
        return Result.failed(e.getMessage());
    }

    /**
     * 异常处理
     * @param e e
     * @return res
     */
    @ExceptionHandler(Exception.class)
    public Result<Object> handleException(Exception e)
    {
        log.error(e.getMessage(), e);
        return Result.failed(e.getMessage());
    }

}
