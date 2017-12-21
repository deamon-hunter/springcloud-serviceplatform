package com.gmsj.core.business.exception;

import java.text.MessageFormat;

/**
 * 业务异常通常根据业务的需要可以选择抛出去或者捕获后处理掉。
 * <p>
 * Rest服务，都用<code>RuntimeException</code>表示抛出到response中交由其他服务处理。
 * 。系统中会定义很多中业务上的错误作为业务异常来反应清晰的业务流程，这些异常都继承自本异常。
 * </p>
 * 
 * <p>
 * 该异常类被定义为抽象类，不能直接实例化，需要定义具体的异常类型来反映业务异常信息。
 * </p>
 * 
 * @author jintao
 * 
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 3805582759691079224L;
	
	private ErrorCode errorCode;

	public BusinessException(ErrorCode errorCode, Object... params) {
		super(MessageFormat.format(errorCode.getErrorMsg(), params));
		this.errorCode = errorCode;
	}
	
	public BusinessException(ErrorCode errorCode, Throwable e, Object... params) {
		super(MessageFormat.format(errorCode.getErrorMsg(), params), e);
		this.errorCode = errorCode;
	}
	
	public ErrorCode getErrorCode() {
		return this.errorCode;
	}
}
