/**
 * pursuit of excellence;striving for excellence;insist on keeping improving, accomplish it best.
 * copyright keeping all right reserved
 */
package com.gmsj.core.conf;

import com.gmsj.core.business.exception.BusinessException;
import com.gmsj.core.business.exception.CommonErrorCode;
import com.gmsj.core.business.exception.SystemException;
import com.gmsj.core.business.model.RestResp;
import com.gmsj.core.conf.security.exceptions.TokenNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalDefaultExceptionHandler {


    /**
     * 400 - Bad Request
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public RestResp handleHttpMessageNotReadableException(HttpServletRequest req, HttpMessageNotReadableException e) {
        log.error("parameter_validation_fail", e);

        return RestResp.error(RestResp.CODE_FAILURE, e.getMessage());
    }

    /**
     * 400 - Bad Request
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestResp handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);

        return RestResp.error(RestResp.CODE_FAILURE, message);
    }

    /**
     * 400 - Bad Request
     */

    @ExceptionHandler(BindException.class)
    public RestResp<Object> handleBindException(BindException e) {
        log.error("parameter_bind_fail", e);

        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);

        return RestResp.error(RestResp.CODE_FAILURE, message);

    }

    /**
     * 400 - Bad Request
     */

    @ExceptionHandler(ConstraintViolationException.class)
    public RestResp handleServiceException(ConstraintViolationException e) {
        log.error("parameter_validation_fail", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();


        return RestResp.error(RestResp.CODE_FAILURE, message);
    }

    /**
     * 400 - Bad Request
     */

    @ExceptionHandler(ValidationException.class)
    public RestResp handleValidationException(ValidationException e) {
        log.error("parameter_validation_fail", e);

        return RestResp.error(RestResp.CODE_FAILURE, e.getMessage());

    }

    /**
     * 400 - Bad Request
     */

    @ExceptionHandler(IllegalArgumentException.class)
    public RestResp handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("parameter_validation_fail", e);

        return RestResp.error(RestResp.CODE_FAILURE, e.getMessage());

    }

    /**
     * 400 - Bad Request
     */

    @ExceptionHandler(IllegalStateException.class)
    public RestResp handleIllegalStateException(IllegalStateException e) {
        log.error("parameter_validation_fail", e);

        return RestResp.error(RestResp.CODE_FAILURE, e.getMessage());

    }

    /**
     * 405 - Method Not Allowed
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public RestResp handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("不支持当前请求方法", e);


        return RestResp.error(RestResp.CODE_FAILURE, e.getMessage());

    }

    /**
     * 415 - Unsupported Media Type
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public RestResp handleHttpMediaTypeNotSupportedException(Exception e) {
        log.error("mediatype_not_support", e);

        return RestResp.error(RestResp.CODE_FAILURE, e.getMessage());

    }


    @ExceptionHandler({BusinessException.class})
    public RestResp<Void> businessException(BusinessException e) {
        int errorCode = 1;
        if (e.getErrorCode() != null) {
            errorCode = e.getErrorCode().getCode();
        }
        log.error("BusinessException，errorCode=" + errorCode, e);
        return RestResp.error(errorCode, e.getErrorCode().getErrorMsg());
    }

    @ExceptionHandler({SystemException.class})
    public RestResp<String> systemException(SystemException e) {
        int errorCode = 1;
        if (e.getErrorCode() != null) {
            errorCode = e.getErrorCode().getCode();
        }
        log.error("SystemException，errorCode=" + errorCode, e);
        return RestResp.error(errorCode, e.getMessage());
    }
    @ExceptionHandler({TokenNotExistException.class})
    public RestResp<String> tokenNotExistException(TokenNotExistException e) {
        log.error("token异常", e);
        return RestResp.<String>error(CommonErrorCode.INNER_ERROR.getCode(), "token验证未通过。" + e.getMessage());
    }
    @ExceptionHandler({Exception.class})
    public RestResp<String> allException(Exception e) {
        log.error("系统异常", e);
        return RestResp.<String>error(CommonErrorCode.INNER_ERROR.getCode(), "内部错误，请联系管理员。" + e.getMessage());
    }

}
