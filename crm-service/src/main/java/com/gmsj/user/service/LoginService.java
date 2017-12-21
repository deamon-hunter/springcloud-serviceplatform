package com.gmsj.user.service;

import com.gmsj.core.business.vo.user.UserVO;

/**
 * 用户登录操作
 *
 * @author hongQiang tang
 * @version $Id: LoginService.java, v 0.1 2017年6月19日 下午2:04:28 Administrator Exp $
 */
public interface LoginService {

    /**
     * 使用手机号码及验证码进行登录
     *
     * @param userVO 手机号码
     * @return UserVO
     */
    UserVO loginByPhone(UserVO userVO);

    /**
     * 使用账号密码进行登录
     *
     * @param userName 账号
     * @param password 密码
     * @return UserVO
     */
    UserVO loginByAccount(UserVO userVO);
}
