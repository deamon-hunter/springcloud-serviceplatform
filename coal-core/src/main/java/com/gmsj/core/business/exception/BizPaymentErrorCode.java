package com.gmsj.core.business.exception;

/**
 * 支付业务模块错误(1400-1500：<br>
 * 
 * 
 * @author
 */
public enum BizPaymentErrorCode implements ErrorCode {

	DEMO(1400, "异常"),


	;

	private int code;
	private String errorMsg;

	BizPaymentErrorCode(int code, String errorMsg) {
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
