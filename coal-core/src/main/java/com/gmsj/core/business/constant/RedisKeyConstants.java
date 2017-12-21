/*
 * Copyright (c) 2017 bbd.map.com All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package com.gmsj.core.business.constant;

/**
 * redisKey 常量定义
 * @author Ovrille
 */

public class RedisKeyConstants {
    
    /**每天的贷款申请次序*/
    public static final String LOAN_APPLY_DAY_SEQUENCE = "loan.applydaysequence:%s";


    /**
     * 字典
     */
    public static final String DICTIONARY="dictionary:%s";

    /**
     * 验证码
     */
    public static final String VERIFY_CODE="verify_code:%s";
}

