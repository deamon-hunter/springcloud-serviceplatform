package com.gmsj.core.business.exception;

/**
 * 消息业务模块错误(1100-1200：<br>
 *
 * @author
 */
public enum BizMessageErrorCode implements ErrorCode {

    DEMO(1100, "异常"),


    VERIFY_CODE_NOT_VALID(1101, "验证码错误或已过期");

    private int code;
    private String errorMsg;

    BizMessageErrorCode(int code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public int getCode() {
        return code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

}
