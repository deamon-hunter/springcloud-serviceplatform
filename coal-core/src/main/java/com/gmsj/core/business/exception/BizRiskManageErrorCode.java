package com.gmsj.core.business.exception;

/**
 * 风控业务模块错误(1500-1600：<br>
 * 
 * 
 * @author
 */
public enum BizRiskManageErrorCode implements ErrorCode {

	DEMO(1500, "异常"),


	;

	private int code;
	private String errorMsg;

	BizRiskManageErrorCode(int code, String errorMsg) {
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
