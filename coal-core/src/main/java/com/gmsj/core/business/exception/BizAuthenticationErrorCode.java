package com.gmsj.core.business.exception;

/**
 * 认证业务模块错误(1600-1800：<br>
 * 
 * 
 * @author
 */
public enum BizAuthenticationErrorCode implements ErrorCode {

	DEMO(1600, "异常"),


	ALREAD_AUTH_LIVE(1020, "您已经进行过活体认证"),
	ALREAD_AUTH_REALNAME(1021, "您已经进行过实名认证"),
	ALREAD_AUTH_STORE(1022, "您已经进行过商户认证");

	;

	private int code;
	private String errorMsg;

	BizAuthenticationErrorCode(int code, String errorMsg) {
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
