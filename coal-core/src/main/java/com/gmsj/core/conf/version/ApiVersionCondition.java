/*
 * Copyright (c) 2014 xiaomaihd and/or its affiliates.All Rights Reserved.
 *            http://www.xiaomaihd.com
 */
package com.gmsj.core.conf.version;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Ovrille on 15/3/27 上午11:18.
 * <p>
 * 版本控制路由
 */
@Slf4j
public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {


    private int apiVersion;

    public ApiVersionCondition(int apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public ApiVersionCondition combine(ApiVersionCondition other) {
        // 采用最后定义优先原则，则方法上的定义覆盖类上面的定义
        return new ApiVersionCondition(other.getApiVersion());
    }

    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest request) {

        int version = getVersion(request.getHeader("Api-Version"));

        // 如果请求的版本号大于配置版本号， 则满足
        if (version >= this.apiVersion) {
            return this;
        }

        return null;
    }

    private int getVersion(String apiVersion) {
        if (Strings.isNullOrEmpty(apiVersion)) {
            return 100;
        }
        return Integer.valueOf(apiVersion.replaceAll("\\.", ""));
    }

    @Override
    public int compareTo(ApiVersionCondition other, HttpServletRequest request) {
        // 优先匹配最新的版本号
        return other.getApiVersion() - this.apiVersion;
    }

    public int getApiVersion() {
        return apiVersion;
    }

}
/*
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         佛祖保佑       永无BUG
*/
