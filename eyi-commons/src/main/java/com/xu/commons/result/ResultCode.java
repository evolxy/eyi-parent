package com.xu.commons.result;

/**
 * @author Author
 * @version 0.1
 * Created On 2022/3/14 14:22
 */
public enum ResultCode {
    /**
     * 成功
     */
    FAILED(40000, "请求失败"),
    NOT_PERMISSION(40001, "权限不足，请联系管理员授权!"),
    TOKEN_EXPIRED(40002, "TOKEN过期，请重新登录"),
    NOT_TOKEN(40003, "未提供TOKEN，请重新登录"),
    TOKEN_INVALID(40004, "无效 TOKEN，请重新登录"),
    BE_REPLACED(40005, "已在别处登录，请确认账号是否泄漏！"),
    KICK_OUT(40006, "已被踢下线，请确认账号是否泄漏！"),
    NOT_LOGIN(40007, "请登录"),
    SUCCESS(20000, "请求成功");


    private final String msg;
    private final int code;

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    ResultCode(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }


}
