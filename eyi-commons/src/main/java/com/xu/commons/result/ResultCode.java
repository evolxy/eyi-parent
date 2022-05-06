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
