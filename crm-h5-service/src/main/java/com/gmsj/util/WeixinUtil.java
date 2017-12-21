/*
 * Copyright (c) 2017 xiaomaihd and/or its affiliates.All Rights Reserved.
 *            http://www.xiaomaihd.com
 */
package com.gmsj.util;

import com.foxinmy.weixin4j.cache.RedisCacheStorager;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.model.WeixinAccount;
import com.foxinmy.weixin4j.mp.WeixinProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by yangfan on 2016/12/23 下午2:31.
 * <p/>
 */
@Configuration
@ConfigurationProperties(prefix = "weixin")
public class WeixinUtil {

    private String appId;
    private String appSecret;

    @Autowired
    private RedisProperties redisProperties;

    private WeixinProxy weixinProxy;


    @PostConstruct
    public void init() {
        WeixinAccount weixinAccount = new WeixinAccount(appId, appSecret);
        weixinProxy = new WeixinProxy(weixinAccount, new RedisCacheStorager<Token>(redisProperties.getHost(), redisProperties.getPort(), 5000, redisProperties.getPassword()));
    }


    public WeixinProxy getWeixinProxy() {

        return weixinProxy;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
