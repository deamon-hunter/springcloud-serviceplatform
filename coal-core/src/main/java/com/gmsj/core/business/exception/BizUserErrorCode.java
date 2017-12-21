package com.gmsj.core.business.exception;

/**
 * 用户业务模块错误(1000-1100：<br>
 *
 * @author
 */
public enum BizUserErrorCode implements ErrorCode {

    DEMO(1000, "异常"),
    USER_JWT_SIGN_FAIL(1010, "用户JWT签名错误，请检查JWT签名！"),
    USER_JWT_VERIFY_FAIL(1011, "登录已过期或未登陆，请重新登录！"),
    USER_PHONE_ALREAD_EXISTS(1012, "手机号已经被注册"),
    USER_NOT_REGISTER(1013, "用户未注册");

    private int code;
    private String errorMsg;

    BizUserErrorCode(int code, String errorMsg) {
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
