/*
 * Copyright (c) 2016 xiaomaihd and/or its affiliates.All Rights Reserved.
 *            http://www.xiaomaihd.com
 */
package com.gmsj.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by Ovrille on 2016/11/28 上午10:26.
 * <p/>
 */
public class JWTAuthentication extends AbstractAuthenticationToken {
    private static final long serialVersionUID = -6855809445272533821L;

    private UserDetails principal;
    private String jsonWebToken;

    public JWTAuthentication(UserDetails principal, String jsonWebToken) {
        super(principal.getAuthorities());
        this.principal = principal;
        this.jsonWebToken = jsonWebToken;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    public String getJsonWebToken() {
        return jsonWebToken;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}