/*
 * Copyright (c) 2016 xiaomaihd and/or its affiliates.All Rights Reserved.
 *            http://www.xiaomaihd.com
 */
package com.gmsj.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Created by Ovrille on 2016/11/28 上午10:30.
 * <p/>
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends JWTSecurityConfig {
    @Override
    protected void setupAuthorization(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                // allow anonymous access to /user/login endpoint
                .antMatchers("/login/phone").permitAll()
                .antMatchers("/login/account").permitAll()
                .antMatchers("/verify-code").permitAll()
                .antMatchers("/weixin/*").permitAll()

                // authenticate all other requests
                .anyRequest().authenticated();
    }


}