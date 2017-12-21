package com.gmsj.core.business.exception;

/**
 * 借还款业务模块错误(1200-1400：<br>
 * 
 * 
 * @author Ovrille
 */
public enum BizLoanErrorCode implements ErrorCode {

    //申请借款    1201-1210
    APPLY_LOAN_USER_NOT_AUTH(1201, "申请贷款的用户未通过认证！"),
    APPLY_LOAN_APPROVE_STATE_NULL(1202, "审批状态为空！"),
    APPLY_LOAN_BANK_ERROR(1203, "银行帐号不匹配！"),
    APPLY_LOAN_CLIENT_ERROR(1204, "客户姓名不匹配！"),

    //审批及进度   1211-1220
    APPLY_LOAN_USER_NOHAVE_BORROW(1211, "没有借款申请单！"),
    //提现        1221-1230
    APPLY_LOAN_USER_REFUND_COUNT_ERROR(1211, "还款期数错误！"),

    //还款历史账单 1231-1240

    //还款     1241-1250
    APPLY_LOAN_USER_NOHAVE_PERIODIZATION(1241, "没有还款订单!"),


	;

	private int code;
	private String errorMsg;

	BizLoanErrorCode(int code, String errorMsg) {
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
