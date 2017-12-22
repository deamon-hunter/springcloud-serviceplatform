package com.gmsj.core.conf.security.annotation;

import com.gmsj.core.conf.security.Session;
import com.gmsj.core.conf.security.TokenService;
import com.gmsj.core.conf.security.exceptions.TokenNotExistException;
import com.gmsj.core.conf.security.exceptions.TokenValidException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Aspect
@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class VerifyToken {
	private final TokenService tokenService;
	@Autowired
	public VerifyToken(final TokenService tokenService) {
		this.tokenService = tokenService;
	}

	/**
	 * 注解@RoleCheck需要验证token和访问权限
	 * 
	 * 
	 * @param roleCheck
	 * @throws Throwable
	 * @return void
	 */
	@Before("execution(public * com.gmsj..*.controller..*.*(..)) && @annotation(roleCheck) && @annotation(requestMapping)")
	@Order(50)
	public void verifyTokenAndRole(RoleCheck roleCheck, RequestMapping requestMapping) throws Throwable {
		verifyToken();
	}

	private void verifyToken() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		String token = request.getHeader("AuthorToken");
        if (ObjectUtils.isEmpty(token)){
            token = request.getHeader("authorToken");
        }
        if (ObjectUtils.isEmpty(token)){
            token = request.getParameter("AuthorToken");
        }
        if (ObjectUtils.isEmpty(token)){
            token = request.getParameter("authorToken");
        }

        if (ObjectUtils.isEmpty(token)) {
            throw new TokenNotExistException();
        }

		Optional<Session> session = tokenService.verify(token);
		if (!session.isPresent()) {
			throw TokenValidException.tokenValidException();
		}
		Session.persistenceCurrentSession(session.get());

	}

}
