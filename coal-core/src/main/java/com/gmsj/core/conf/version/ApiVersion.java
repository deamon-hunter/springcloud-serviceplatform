/*
 * Copyright (c) 2014 xiaomaihd and/or its affiliates.All Rights Reserved.
 *            http://www.xiaomaihd.com
 */
package com.gmsj.core.conf.version;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * Created by Ovrille on 15/3/27 上午10:59.
 * <p>
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {

    int value();
}