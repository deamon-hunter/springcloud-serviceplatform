/*
 * Copyright (c) 2016 xiaomaihd and/or its affiliates.All Rights Reserved.
 *            http://www.xiaomaihd.com
 */
package com.gmsj.security;

import com.gmsj.core.business.exception.BizUserErrorCode;
import com.gmsj.core.business.model.RestResp;
import com.gmsj.core.util.JsonUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ovrille on 2016/11/28 下午1:31.
 * <p/>
 */
public class NoAuthenticationEntryPoint implements AuthenticationEntryPoint {

    //当访问的资源没有权限，会调用这里
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {


        //返回json形式的错误信息
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        RestResp restResp = RestResp.error(BizUserErrorCode.USER_JWT_VERIFY_FAIL);

        response.getWriter().println(JsonUtil.toJsonString(restResp));
        response.getWriter().flush();
    }
}