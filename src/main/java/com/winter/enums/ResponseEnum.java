package com.winter.enums;

/**
 * 响应类
 */
public enum ResponseEnum {
    SUCCESS(0, "操作成功！"),
    FAIL(1, "操作失败！");

    public int code;
    public String msg;

    ResponseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
