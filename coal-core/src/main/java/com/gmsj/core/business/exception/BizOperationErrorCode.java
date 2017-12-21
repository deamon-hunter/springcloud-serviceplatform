package com.gmsj.core.business.exception;

/**
 * 业务模块错误(1800-20000：<br>
 * 
 * 
 * @author
 */
public enum BizOperationErrorCode implements ErrorCode {
    
    //申请借款    1801-1810
    BANKS_IS_NULL(1801, "没有银行可供选择！"),
    
    DEMO(1800, "异常"),
    ;

	private int code;
	private String errorMsg;

	BizOperationErrorCode(int code, String errorMsg) {
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
