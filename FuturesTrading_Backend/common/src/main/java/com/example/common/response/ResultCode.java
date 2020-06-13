package com.example.common.response;

import lombok.Getter;

public enum  ResultCode {
    SUCCESS(0, "success"),
    ERROR(-1, "error"),
    WRONG_ACCOUNTORPASSWORD(-2, "账号或密码错误"),
    EXIST_ACCOUNT(-3, "账号已存在"),
    Failure_Create_ORDER(-4, "创建订单失败"),
    WRONG_ORDER_QUANTITY(-5, "数量必须为正数"),
    WRONG_ORDER_PRODUCT(-6, "期货不能为空"),
    WRONG_ORDER_VARIETY(-7, "订单种类错误");



    @Getter
    private int code;

    @Getter
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
