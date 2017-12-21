/*
 * Copyright (c) 2016 xiaomaihd and/or its affiliates.All Rights Reserved.
 *            http://www.xiaomaihd.com
 */
package com.gmsj.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * Created by Ovrille on 2016/11/28 上午10:06.
 * <p/>
 * JTW验证
 */
@Component
public class JWTAuthenticationFilter extends RequestHeaderAuthenticationFilter {

    public JWTAuthenticationFilter() {
        // Don't throw exceptions if the header is missing
        this.setExceptionIfHeaderMissing(false);

        // This is the request header it will look for
        this.setPrincipalRequestHeader("api-key");
    }

    @Override
    @Autowired
    public void setAuthenticationManager(
            AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

}