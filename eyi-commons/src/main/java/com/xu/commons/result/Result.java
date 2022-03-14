package com.xu.commons.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/14 14:17
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Result<T> {
    private String msg;
    private int code;
    private boolean success;
    private T data;

    public Result(boolean success, T data, ResultCode resultCode) {
        this.success = success;
        this.data = data;
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    public static <T> Result<T> ok() {
        return new Result<>(true, null, ResultCode.SUCCESS);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(true, data, ResultCode.SUCCESS);
    }

    public static <T> Result<T> ok(String msg) {
        return new Result<>(msg, ResultCode.SUCCESS.getCode(), true, null);
    }

    public static <T> Result<T> ok(int code, String msg) {
        return new Result<>(msg, code, true, null);
    }

    public static <T> Result<T> ok(ResultCode resultCode) {
        return new Result<>(true, null, resultCode);
    }

    public static <T> Result<T> failed() {
        return new Result<>(false, null, ResultCode.FAILED);
    }

    public static <T> Result<T> failed(T data) {
        return new Result<>(true, data, ResultCode.FAILED);
    }

    public static <T> Result<T> failed(String msg) {
        return new Result<>(msg, ResultCode.FAILED.getCode(), false, null);
    }

    public static <T> Result<T> failed(int code, String msg) {
        return new Result<>(msg, code, false, null);
    }

    public static <T> Result<T> failed(ResultCode resultCode) {
        return new Result<>(false, null, resultCode);
    }
}
