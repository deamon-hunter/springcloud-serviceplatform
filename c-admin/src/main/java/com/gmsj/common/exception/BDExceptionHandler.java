package com.gmsj.common.exception;

import com.gmsj.core.business.model.RestResp;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 异常处理器
 * 
 */
@ControllerAdvice
public class BDExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 自定义异常
	 */
	@ExceptionHandler(BDException.class)
	public RestResp handleBDException(BDException e) {


		return RestResp.error(e.getCode(), e.getMsg());
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public RestResp handleDuplicateKeyException(DuplicateKeyException e) {
		logger.error(e.getMessage(), e);
		return RestResp.error(1, "数据库中已存在该记录");
	}

	@ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
	public RestResp noHandlerFoundException(org.springframework.web.servlet.NoHandlerFoundException e) {
		logger.error(e.getMessage(), e);
		return RestResp.error(1, "没找找到页面");
	}

	@ExceptionHandler(AuthorizationException.class)
	public String handleAuthorizationException(AuthorizationException e) {
		logger.error(e.getMessage(), e);
		return "error/403";
	}

	@ExceptionHandler(Exception.class)
	public String handleException(Exception e) {
		logger.error(e.getMessage(), e);
		return "error/500";
	}
}
